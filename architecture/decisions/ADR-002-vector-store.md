# ADR-002: ChromaDB as Default Vector Store

## Status
Accepted

## Context
RAG requires vector storage for semantic document search. Options: ChromaDB, PGVector, Pinecone, Weaviate.

## Decision
Use ChromaDB for development and default deployment. Provide PGVector as production alternative via configuration switch.

## Consequences
- **Positive**: Easy Docker setup, Spring AI native support, no additional DB for dev
- **Negative**: ChromaDB less mature for large-scale production than managed services

## Configuration
```yaml
eka:
  vector-store:
    provider: chroma  # or pgvector
```
