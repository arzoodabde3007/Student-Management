# How the Spring Boot Application Connects to Docker

This document explains how the Student Management application connects to a MySQL database running inside a Docker container.

---

## Overview

The setup uses **two Docker containers** orchestrated via `docker-compose.yml`:

| Container | Image Source | Role |
|---|---|---|
| `studentmanagement-mysql` | `Dockerfile.mysql` | Runs MySQL 8.0 database |
| `studentmanagement-app` | `Dockerfile` | Runs the Spring Boot application |

---

## 1. MySQL Container (`Dockerfile.mysql`)

```dockerfile
FROM mysql:8.0

ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=springbootdemo

COPY src/main/resources/db/init.sql /docker-entrypoint-initdb.d/init.sql

EXPOSE 3306
```

**What happens here:**
- Pulls the official **MySQL 8.0** image
- Automatically creates the `springbootdemo` database on first startup via environment variables
- Copies `init.sql` into `/docker-entrypoint-initdb.d/` — MySQL automatically executes any `.sql` file placed in this directory, which seeds/creates the tables
- Exposes port `3306` **internally** within Docker's private network

---

## 2. Spring Boot App Container (`Dockerfile`)

```dockerfile
# Stage 1: Build the JAR
FROM eclipse-temurin:25-jdk AS builder
WORKDIR /app
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline -q
COPY src/ src/
RUN ./mvnw package -DskipTests -q

# Stage 2: Run the JAR
FROM eclipse-temurin:25-jre
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

**What happens here:**
- Uses a **multi-stage build** to keep the final image small
  - **Stage 1**: Compiles and packages the application into a JAR *inside* Docker (not on your local machine)
  - **Stage 2**: Copies only the JAR into a lightweight JRE image and runs it
- Exposes port `8080` for HTTP traffic

---

## 3. Docker Compose — The Glue (`docker-compose.yml`)

```yaml
services:

  mysql:
    build:
      context: .
      dockerfile: Dockerfile.mysql
    container_name: studentmanagement-mysql
    ports:
      - "3307:3306"
    volumes:
      - mysql_data:/var/lib/mysql
    healthcheck:
      test: ["CMD", "mysqladmin", "ping", "-h", "localhost", "-uroot", "-proot"]
      interval: 10s
      timeout: 5s
      retries: 5

  app:
    build: .
    container_name: studentmanagement-app
    ports:
      - "8080:8080"
    environment:
      DB_URL: jdbc:mysql://mysql:3306/springbootdemo
      DB_USERNAME: root
      DB_PASSWORD: root
    depends_on:
      mysql:
        condition: service_healthy

volumes:
  mysql_data:
```

---

## 4. The Key Mechanism — Environment Variable Override

`application.properties` uses Spring's **placeholder with default value** syntax:

```properties
spring.datasource.url=${DB_URL:jdbc:mysql://localhost:3306/springbootdemo}
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD:root}
```

**Syntax:** `${VARIABLE_NAME:default_value}`
- If the environment variable **exists** → use it
- If it **does NOT exist** → fall back to the default value

This is why `localhost` still appears in `application.properties` — it is just the **fallback** for local development. Docker Compose injects the real values at runtime.

| Where app runs | `DB_URL` set? | URL actually used |
|---|---|---|
| Local machine (IDE / `mvnw`) | ❌ No | `localhost:3306` |
| Inside Docker container | ✅ Yes (injected by Compose) | `mysql:3306` |

---

## 5. Why `mysql` Instead of `localhost` Inside Docker?

```yaml
DB_URL: jdbc:mysql://mysql:3306/springbootdemo
```

Docker Compose creates a **shared private network** for all services in the same `docker-compose.yml`. Each container is automatically reachable by its **service name** as a DNS hostname.

```
┌─────────────────────────────────────────────────┐
│             Docker Internal Network              │
│                                                  │
│  ┌───────────────────┐   ┌────────────────────┐  │
│  │   app container   │──▶│  mysql container   │  │
│  │  (Spring Boot)    │   │  hostname: mysql   │  │
│  └───────────────────┘   └────────────────────┘  │
│        ↕ port 8080               ↕ port 3306     │
└─────────────────────────────────────────────────┘
          ↕                            ↕
    your browser                your MySQL client
    localhost:8080               localhost:3307
```

- From **inside** the `app` container → MySQL is at `mysql:3306`
- From **your PC** → MySQL is at `localhost:3307` (due to `"3307:3306"` port mapping)

> **Note:** The host port is `3307` (not `3306`) as configured in `ports: "3307:3306"`. This means if you connect via a DB client like MySQL Workbench from your machine, use port `3307`.

---

## 6. Health Check — Ensuring MySQL is Ready Before App Starts

```yaml
healthcheck:
  test: ["CMD", "mysqladmin", "ping", "-h", "localhost", "-uroot", "-proot"]
  interval: 10s
  timeout: 5s
  retries: 5
```

MySQL takes a few seconds to fully initialize after the container starts. Without the health check, Spring Boot would crash on startup trying to connect to a MySQL that isn't ready yet.

`condition: service_healthy` in `depends_on` tells Docker Compose to **wait** until the MySQL container passes the ping health check before launching the app container.

---

## 7. Data Persistence (Volume)

```yaml
volumes:
  - mysql_data:/var/lib/mysql
```

MySQL stores its data files in `/var/lib/mysql` inside the container. By mapping it to a named volume `mysql_data`, your database data **persists even if the container is stopped or removed**. Without this, all data would be lost every time the container restarts.

---

## Complete Startup Flow

```
docker-compose up
       │
       ├─► Builds MySQL image (Dockerfile.mysql)
       │       └─► Runs init.sql → creates tables in springbootdemo DB
       │
       ├─► Waits for MySQL healthcheck to pass ✅
       │        (mysqladmin ping every 10s, up to 5 retries)
       │
       └─► Builds & starts App image (Dockerfile)
               └─► Spring Boot reads DB_URL env var injected by Compose
                       └─► Connects to mysql:3306 (Docker internal network)
                               └─► App is live at localhost:8080 🚀
```

---

## Quick Reference

| Scenario | DB Host | DB Port |
|---|---|---|
| Running locally (IDE/mvnw) | `localhost` | `3306` |
| Running via Docker Compose | `mysql` (service name) | `3306` |
| Connecting from PC to Docker MySQL | `localhost` | `3307` |
| App available at | `localhost` | `8080` |
