# Deployment Guide

Production deployment instructions for the Enterprise Knowledge Assistant.

## Deployment Options

| Method | Use Case |
|--------|----------|
| Docker Compose | Staging, small production |
| Kubernetes + Helm | Large-scale production |
| Manual | Development, debugging |

## Docker Compose (Recommended for Staging)

```bash
export OPENAI_API_KEY=sk-...
export JWT_SECRET=$(openssl rand -base64 32)
docker compose -f docker/docker-compose.yml up -d
```

## Environment Checklist

- [ ] MySQL 8.x provisioned with `eka_db` database
- [ ] ChromaDB or PGVector running
- [ ] OpenAI/Gemini API key configured
- [ ] JWT secret set (256+ bits, unique per environment)
- [ ] CORS origins configured for production domain
- [ ] SSL/TLS termination configured (nginx/ALB)
- [ ] Backup strategy for MySQL and vector store
- [ ] Monitoring (Prometheus/Grafana) connected
- [ ] Log aggregation (ELK/CloudWatch) configured

## Kubernetes

Helm charts in [kubernetes/helm/](kubernetes/helm/) (template structure).

## Nginx Reverse Proxy

Production nginx config in [nginx/](nginx/) for SSL termination and routing.

## Health Endpoints

| Service | Endpoint |
|---------|----------|
| Backend | `GET /actuator/health` |
| Frontend | `GET /` (200 OK) |
| MySQL | TCP 3306 |
| ChromaDB | `GET /api/v1/heartbeat` |

## Scaling

- **Backend**: Horizontal scaling behind load balancer (stateless JWT)
- **Frontend**: CDN + nginx static serving
- **MySQL**: Read replicas for analytics queries
- **ChromaDB**: Consider PGVector for production scale
