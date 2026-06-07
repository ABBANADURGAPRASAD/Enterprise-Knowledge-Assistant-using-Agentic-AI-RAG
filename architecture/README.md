# Architecture Documentation

Enterprise Knowledge Assistant — system architecture, design decisions, and diagrams.

## System Overview

The EKA platform is a monorepo consisting of a Spring Boot backend, Angular frontend, MySQL database, and ChromaDB vector store, orchestrated via Docker Compose.

## Architecture Diagram

```mermaid
graph TB
    subgraph Client
        A[Angular SPA]
    end

    subgraph API Layer
        B[Spring Boot API]
        C[Agent Router]
        D[Spring Security + JWT]
    end

    subgraph AI Layer
        E[HR Agent]
        F[Manager Agent]
        G[Recruitment Agent]
        H[Knowledge Agent]
        I[General Assistant]
        J[Tool Registry]
    end

    subgraph Data Layer
        K[(MySQL)]
        L[(ChromaDB)]
    end

    subgraph External
        M[OpenAI / Gemini]
    end

    A -->|REST/WebSocket| B
    B --> D
    B --> C
    C --> E & F & G & H & I
    E & F & G & H & I --> J
    J --> K
    E & G & H --> L
    C --> M
    B --> K
    B --> L
```

## Agent Router Flow

```mermaid
sequenceDiagram
    participant U as User
    participant A as Angular
    participant R as Agent Router
    participant S as Security
    participant Ag as Agent
    participant T as Tools
    participant LLM as OpenAI/Gemini

    U->>A: Send message
    A->>R: POST /chat/message
    R->>S: Validate JWT + roles
    R->>LLM: Classify intent
    R->>R: Select agent
    R->>S: Validate agent access
    R->>Ag: Process request
    Ag->>T: Invoke tools (if needed)
    T-->>Ag: Tool results
    Ag->>LLM: Generate response
    Ag-->>R: ChatResponse + actionPayload
    R-->>A: JSON response
    A->>A: Load dynamic component (if actionPayload)
    A-->>U: Display response
```

## RAG Pipeline

```mermaid
flowchart LR
    A[Document Upload] --> B[Parse - Tika]
    B --> C[Chunk - TokenTextSplitter]
    C --> D[Embed - OpenAI]
    D --> E[Store - ChromaDB]
    
    F[User Query] --> G[Security Filter]
    G --> H[Vector Search]
    H --> I[Rerank]
    I --> J[Context Assembly]
    J --> K[LLM Generation]
    K --> L[Response + Citations]
```

## Clean Architecture (Backend)

```
┌─────────────────────────────────────────┐
│              Controllers                 │  ← REST API, WebSocket
├─────────────────────────────────────────┤
│              Services                    │  ← Business logic
├─────────────────────────────────────────┤
│         Agents / RAG / Tools             │  ← AI layer
├─────────────────────────────────────────┤
│            Repositories                  │  ← Data access
├─────────────────────────────────────────┤
│              Entities                    │  ← Domain models
└─────────────────────────────────────────┘
```

## Role-Based Access Matrix

| Resource | CEO | HR | Manager | Employee | External |
|----------|-----|----|---------|----------|----------|
| All employees | ✓ | ✓ | Team only | Self | ✗ |
| HR policies | ✓ | ✓ | ✓ | ✓ | ✗ |
| Confidential docs | ✓ | ✓ | ✗ | ✗ | ✗ |
| Approvals | ✓ | ✓ | Own team | ✗ | ✗ |
| Job listings | ✓ | ✓ | ✓ | ✓ | ✓ |
| Analytics (org) | ✓ | ✓ | ✗ | ✗ | ✗ |
| Analytics (team) | ✓ | ✓ | ✓ | ✗ | ✗ |

## Design Decisions

See [decisions/](decisions/) for Architecture Decision Records (ADRs).

## Sequence Diagrams

See [sequence/](sequence/) for detailed interaction flows.
