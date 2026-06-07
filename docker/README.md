# Docker — Container Configuration

Docker and Docker Compose setup for the full EKA stack.

## Services

| Service  | Port | Description                    |
|----------|------|--------------------------------|
| mysql    | 3306 | MySQL 8.0 relational database  |
| chroma   | 8000 | ChromaDB vector store          |
| backend  | 8080 | Spring Boot API                |
| frontend | 4200 | Angular SPA (nginx)            |

## Quick Start

```bash
# Infrastructure only (MySQL + ChromaDB)
docker compose up -d mysql chroma

# Full stack
export OPENAI_API_KEY=your-key
export JWT_SECRET=your-secret
docker compose up -d
```

## Environment Variables

| Variable       | Required   | Description                           |
|----------------|------------|---------------------------------------|
| OPENAI_API_KEY | Yes        | OpenAI API key for LLM/embeddings     |
| JWT_SECRET     | Yes (prod) | JWT signing secret (256+ bits)        |
| DB_USERNAME    | No         | MySQL username (default: eka_user)    |
| DB_PASSWORD    | No         | MySQL password (default: eka_password)|

## Volumes

- `mysql_data` — Persistent MySQL storage
- `chroma_data` — Persistent vector store data

## Health Checks

All services include health checks. Backend waits for MySQL and ChromaDB to be healthy before starting.
