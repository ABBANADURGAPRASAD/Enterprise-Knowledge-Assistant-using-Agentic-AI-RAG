# backend-app

Application bootstrap module — executable Spring Boot JAR.

## Purpose

Wires everything together: REST API, security, configuration, and Flyway migrations. Depends on **backend-api** and **backend-service**.

## Package Structure

```
com.enterprise.eka/
├── EkaApplication.java       # Main entry point
├── controller/               # REST controllers (Auth, Chat, Documents, Workflows)
├── security/                 # JWT, SecurityFilterChain, CORS
├── exception/                # GlobalExceptionHandler
└── (resources/)
    ├── application.yml
    ├── application-dev.yml
    └── db/migration/         # Flyway scripts
```

## Run Locally

```bash
# From backend/ root
mvn spring-boot:run -pl backend-app -am -Dspring-boot.run.profiles=dev
```

Or build and run the JAR:

```bash
mvn clean package -pl backend-app -am -DskipTests
java -jar backend-app/target/backend-app-1.0.0-SNAPSHOT.jar --spring.profiles.active=dev
```

## Dependencies

- backend-api
- backend-service
- Spring Web, Security, WebSocket, Actuator
- MySQL, Flyway, JWT, SpringDoc OpenAPI

## Rules

- Controllers delegate to **backend-service** — no business logic here
- All `application*.yml` and Flyway migrations live here
- Only this module produces the runnable fat JAR
