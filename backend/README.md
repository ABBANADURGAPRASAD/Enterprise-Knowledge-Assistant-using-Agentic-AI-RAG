# Backend — Enterprise Knowledge Assistant

Maven multi-module Spring Boot 3.x backend following organization-standard layering.

## Module Structure

```
backend/
├── pom.xml                 # Parent POM (aggregator)
├── backend-api/            # API contracts — DTOs, enums, shared models
├── backend-service/        # Business logic — entities, services, agents, RAG
└── backend-app/            # Bootstrap — controllers, security, config, runnable JAR
```

## Layer Responsibilities

| Module | Responsibility | Depends On |
|--------|---------------|------------|
| **backend-api** | DTOs, enums, API models — no Spring runtime | — |
| **backend-service** | Entities, repositories, services, agents, RAG, tools | backend-api |
| **backend-app** | Controllers, security, exception handling, Flyway, main() | backend-api, backend-service |

## Dependency Flow

```
backend-app  ──►  backend-service  ──►  backend-api
     │                  │
     └──────────────────┘
```

## Technology

- Java 21, Spring Boot 3.3.x, Spring AI 1.x
- MySQL 8.x, Flyway, ChromaDB / PGVector
- JWT, Spring Security, OpenAPI

## Quick Start

```bash
# Build all modules
mvn clean install -DskipTests

# Run application
mvn spring-boot:run -pl backend-app -am -Dspring-boot.run.profiles=dev
```

API: `http://localhost:8080` · Swagger: `http://localhost:8080/swagger-ui.html`

## Module Documentation

- [backend-api/README.md](backend-api/README.md)
- [backend-service/README.md](backend-service/README.md)
- [backend-app/README.md](backend-app/README.md)
