# Vector Store

Configuration and backup location for vector store data.

## Providers

| Provider | Dev         | Production |
|----------|-------------|------------|
| ChromaDB | Default     | Optional   |
| PGVector | Alternative | Recommended|

## Configuration

```yaml
# application.yml
eka:
  vector-store:
    provider: chroma

spring:
  ai:
    vectorstore:
      chroma:
        client:
          host: localhost
          port: 8000
        collection-name: eka-documents
```

## Backups

Store vector store backups in `vector-store/backups/` (gitignored).

```bash
# ChromaDB backup (via Docker volume)
docker run --rm -v eka_chroma_data:/data -v $(pwd)/backups:/backup alpine tar czf /backup/chroma-$(date +%Y%m%d).tar.gz /data
```

## Collection Management

Each document type can use metadata filters:
- `documentType`: HR, POLICY, TECHNICAL, SOP
- `classification`: PUBLIC, INTERNAL, CONFIDENTIAL, RESTRICTED
- `departmentId`: Department scope
