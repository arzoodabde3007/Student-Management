# Security Policy

## Supported Versions

| Version | Supported |
| ------- | --------- |
| latest  | ✅        |

## Reporting a Vulnerability

If you discover a security vulnerability in this project, please **do not** open a public GitHub issue.

Instead, report it privately via [GitHub Security Advisories](../../security/advisories/new).

You can expect:
- **Acknowledgement** within 48 hours
- **Status update** within 7 days
- A fix or mitigation plan communicated once the issue is resolved

## Security Best Practices for This Project

- All actuator endpoints are exposed — restrict access in production via `management.endpoints.web.exposure.include`
- HTTP Basic Auth is used — consider switching to JWT/OAuth2 for production deployments
- Passwords are BCrypt-encoded; never store plaintext credentials
- Database credentials must be provided via environment variables or a secrets manager — never hard-code them
