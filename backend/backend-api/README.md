# backend-api

API contract module — shared types consumed by **backend-service** and **backend-app**.

## Purpose

Thin JAR with no Spring Boot runtime. Defines the public contract between layers.

## Package Structure

```
com.enterprise.eka.api/
├── dto/           # Request/response DTOs (LoginRequest, ChatResponse, ApiResponse, ...)
├── common/        # Shared enums (RoleType, AgentType, WorkflowType, DocumentClassification)
└── model/         # Cross-layer models (AgentContext, RetrievedDocument)
```

## Dependencies

- Jakarta Validation
- Lombok
- Jackson annotations

## Rules

- **No** Spring `@Service`, `@Repository`, or `@Controller`
- **No** JPA entities or database access
- **No** business logic — data shapes only
- Safe to publish as a client-facing API jar for other services

## Build

```bash
mvn clean install -pl backend-api
```
