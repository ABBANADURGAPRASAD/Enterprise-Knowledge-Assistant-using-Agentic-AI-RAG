# Ticket 006: Vector Database

| Field           |    Value |
|-----------------|----------|
| **ID**          | EKA-006 |
| **Title**       | Vector Database |
| **Complexity**  | L |
| **Dependencies**| 003 |
| **Status**      | BACKLOG |
| **Assignee**    | _Unassigned_ |
| **Sprint**      | _TBD_ |

## Objective

Integrate ChromaDB and/or PGVector as vector store for semantic document search.

## Requirements

- [ ] Configure ChromaDB Docker container
- [ ] Implement VectorStoreService abstraction
- [ ] Create ChromaDB and PGVector implementations
- [ ] Support collection management (create, delete, list)
- [ ] Implement similarity search with metadata filters
- [ ] Add vector store health check

## Acceptance Criteria

- [ ] ChromaDB runs via Docker Compose
- [ ] Embeddings stored and retrievable
- [ ] Metadata-filtered search works
- [ ] Switchable between ChromaDB and PGVector via config
- [ ] Health endpoint reports vector store status

## Technical Design

Strategy pattern for vector store. Default ChromaDB for dev, PGVector option for prod.

## API Requirements

GET /api/v1/vector-store/health, POST /api/v1/vector-store/collections

## Database Changes

Optional pgvector extension for PostgreSQL alternative

## AI Requirements

Embedding model: text-embedding-3-small or Gemini embedding

## Testing Requirements

- Unit tests for all new services and components
- Integration tests for API endpoints
- Security tests for authorization boundaries
- E2E tests for critical user flows (where applicable)

## Notes

_Add implementation notes, blockers, and decisions here during development._

## Status History

| Date       | Status  | Notes          |
|------------|---------|----------------|
| 2026-06-07 | BACKLOG | Ticket created |
