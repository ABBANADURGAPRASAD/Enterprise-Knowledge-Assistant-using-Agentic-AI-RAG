# Ticket 020: Docker Deployment

| Field           |    Value |
|-----------------|----------|
| **ID**          | EKA-020 |
| **Title**       | Docker Deployment |
| **Complexity** | M |
| **Dependencies** | 003,002,006 |
| **Status**      | BACKLOG |
| **Assignee**    | _Unassigned_ |
| **Sprint**       _TBD_ |

## Objective

Create Docker and Docker Compose setup for full stack local and production deployment.

## Requirements

- [ ] Dockerfile for Spring Boot backend
- [ ] Dockerfile for Angular frontend (nginx)
- [ ] Docker Compose: backend, frontend, MySQL, ChromaDB
- [ ] Environment variable configuration
- [ ] Health checks and restart policies
- [ ] Production docker-compose.prod.yml

## Acceptance Criteria

- [ ] docker compose up starts entire stack
- [ ] All services healthy within 60 seconds
- [ ] Frontend accessible on port 80/4200
- [ ] Backend API on port 8080
- [ ] Data persists across restarts

## Technical Design

Multi-stage builds. Non-root containers. Secrets via env files.

## API Requirements

Health endpoints for all services

## Database Changes

MySQL init scripts in docker/mysql/

## AI Requirements

_No AI changes_

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
