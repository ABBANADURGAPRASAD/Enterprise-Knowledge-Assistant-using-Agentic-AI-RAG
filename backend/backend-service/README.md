# backend-service

Business logic module — domain, persistence, AI agents, RAG, and tool calling.

## Purpose

Contains all enterprise business rules. Depends on **backend-api** only (not on **backend-app**).

## Package Structure

```
com.enterprise.eka.service/
├── entity/              # JPA entities
├── repository/          # Spring Data repositories
├── authentication/      # CustomUserDetailsService
├── chat/                # ChatService
├── workflows/           # WorkflowService
├── agents/              # Multi-agent system (router + specialized agents)
├── rag/                 # Ingestion, retrieval, security filters
├── tools/               # Spring AI function calling tools
└── ai/                  # Spring AI ChatClient configuration
```

## Dependencies

- backend-api
- Spring Data JPA
- Spring AI (OpenAI, Gemini, ChromaDB, PGVector)
- Apache Tika (document parsing)
- MapStruct

## Rules

- **No** REST controllers — those live in **backend-app**
- **No** Spring Security config — security wiring lives in **backend-app**
- Expose `@Service` beans consumed by controllers in **backend-app**

## Build

```bash
mvn clean install -pl backend-service -am
```
