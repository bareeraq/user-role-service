A Spring Boot REST API that handles user and role management with fine-grained endpoint access control. Supports creating users and roles, assigning roles to users, and mapping roles to specific HTTP endpoints. Database schema is version-controlled via Flyway migrations on MySQL.
Features:

Create and manage users and roles
Assign multiple roles to multiple users in a single request
Map roles to specific API endpoints (path + HTTP method)
Idempotent operations with descriptive status responses (ASSIGNED, ALREADY_EXISTS, USER_NOT_FOUND, etc.)
Schema managed with Flyway (V1–V7 migrations)

Stack: Java 21 · Spring Boot · Spring Data JPA · MySQL · Flyway · Lombok
