# Copilot Instructions — Student Management

## Build & Run

```bash
# Run the application
./mvnw spring-boot:run

# Build JAR
./mvnw package

# Run all tests
./mvnw test

# Run a single test class
./mvnw test -Dtest=StudentmanagementApplicationTests
```

Requires a running MySQL instance at `localhost:3306/springbootdemo` with credentials `root/root`.  
`spring.jpa.hibernate.ddl-auto=update` — schema is auto-managed by Hibernate on startup.

## Architecture

Classic layered Spring Boot REST API:

```
Controller → Service → Repository (Spring Data JPA) → MySQL
```

Each domain resource (Student, Course, Department, Address) has its own controller, service, repository, entity, request/response DTOs, and manual mapper.

**Data model relationships:**
- `Student` → `Course` (ManyToOne) → `Department` (ManyToOne)
- `Student` → `Address` (ManyToOne)

**Package layout** (base: `com.example.springproject.studentmanagement`):
- `Entities/` — JPA entities *(note: PascalCase package name, unlike the rest)*
- `dto/` — `*RequestDTO` (input) and `*ResponseDTO` (output)
- `mappers/` — manual mapper `@Component` classes (no MapStruct)
- `exceptions/` — `StudentNotFoundException`, `GlobalExceptionHandler`, `ErrorResponse`
- `security/` — `SecurityConfig`, `CustomUserDetailService`, `CustomUserDetails`
- `aspects/` — AOP `LoggingAspect` (logs before every service method)
- `interceptor/` — `LoggingInterceptor` (logs before/after each HTTP request)
- `filter/` — `RequestLoggingFilter`

## Key Conventions

**DTOs and Mappers:**  
Request DTOs carry `@Valid` constraints (`@NotBlank`, `@Email`, `@Size`, etc.). Mappers are plain `@Component` classes with explicit field-by-field mapping — do not introduce MapStruct without updating all existing mappers.

**Exception handling:**  
`GlobalExceptionHandler` (`@RestControllerAdvice`) handles `StudentNotFoundException` → 404 with an `ErrorResponse` body, and `MethodArgumentNotValidException` → 400 with a field → message map. Only `StudentNotFoundException` is a custom typed exception; other services throw `RuntimeException` directly.

**Security:**  
All endpoints require HTTP Basic authentication. `CustomUserDetailService` loads users from the `Student` table by `email`. Passwords are BCrypt-encoded on create. The in-memory fallback credentials (`admin/admin123`) in `application.properties` are superseded by DB-backed auth.

**Dependency injection:**  
Services use constructor injection. Controllers use `@Autowired` field injection.

**Pagination:**  
`GET /students/pages?pageNo=0&size=10&sortBy=studentName&direction=asc` — zero-indexed pages.

**Logging:**  
Three layers exist simultaneously: `LoggingAspect` (AOP, service layer), `LoggingInterceptor` (MVC interceptor, HTTP layer), and `RequestLoggingFilter` (servlet filter). All use SLF4J.

**Actuator:**  
All actuator endpoints are exposed (`management.endpoints.web.exposure.include=*`).
