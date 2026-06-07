# Project Management — ToDo Board

This folder contains all project tickets for the Enterprise Knowledge Assistant (EKA) development lifecycle.

## Ticket Workflow

```
BACKLOG → TODO → IN_PROGRESS → IN_REVIEW → DONE
```

## Status Legend

| Status | Description |
|--------|-------------|
| `BACKLOG` | Planned but not yet scheduled |
| `TODO` | Ready to start |
| `IN_PROGRESS` | Actively being worked on |
| `IN_REVIEW` | Code review / QA |
| `DONE` | Completed and verified |
| `BLOCKED` | Waiting on dependency |

## Ticket Index

### Phase 1 — Foundation
| Ticket | Title | Complexity | Status |
|--------|-------|------------|--------|
| [001](ticket-001-project-setup.md) | Project Setup | S | TODO |
| [002](ticket-002-angular-setup.md) | Angular Setup | M | BACKLOG |
| [003](ticket-003-spring-boot-setup.md) | Spring Boot Setup | M | BACKLOG |
| [004](ticket-004-jwt-security.md) | JWT Security | L | BACKLOG |
| [005](ticket-005-rag-implementation.md) | RAG Implementation | XL | BACKLOG |
| [006](ticket-006-vector-database.md) | Vector Database | L | BACKLOG |

### Phase 2 — AI & Agents
| Ticket | Title | Complexity | Status |
|--------|-------|------------|--------|
| [007](ticket-007-agent-router.md) | Agent Router | XL | BACKLOG |
| [008](ticket-008-hr-agent.md) | HR Agent | L | BACKLOG |
| [009](ticket-009-manager-agent.md) | Manager Agent | L | BACKLOG |
| [010](ticket-010-recruitment-agent.md) | Recruitment Agent | L | BACKLOG |
| [011](ticket-011-tool-calling.md) | Tool Calling | XL | BACKLOG |

### Phase 3 — Frontend
| Ticket | Title | Complexity | Status |
|--------|-------|------------|--------|
| [012](ticket-012-chat-ui.md) | Chat UI | L | BACKLOG |
| [013](ticket-013-dynamic-component-loader.md) | Dynamic Component Loader | XL | BACKLOG |
| [014](ticket-014-role-based-access.md) | Role-Based Access | L | BACKLOG |

### Phase 4 — Security & Data
| Ticket | Title | Complexity | Status |
|--------|-------|------------|--------|
| [015](ticket-015-rag-security.md) | RAG Security | XL | BACKLOG |
| [016](ticket-016-prompt-injection-protection.md) | Prompt Injection Protection | L | BACKLOG |
| [017](ticket-017-document-ingestion.md) | Document Ingestion | L | BACKLOG |
| [018](ticket-018-enterprise-search.md) | Enterprise Search | L | BACKLOG |

### Phase 5 — Features & Deployment
| Ticket | Title | Complexity | Status |
|--------|-------|------------|--------|
| [019](ticket-019-analytics-dashboard.md) | Analytics Dashboard | L | BACKLOG |
| [020](ticket-020-docker-deployment.md) | Docker Deployment | M | BACKLOG |
| [021](ticket-021-workflow-engine.md) | Workflow Engine | XL | BACKLOG |
| [022](ticket-022-websocket-realtime.md) | WebSocket Real-time | M | BACKLOG |
| [023](ticket-023-audit-logging.md) | Audit Logging | M | BACKLOG |
| [024](ticket-024-employee-agent.md) | Employee Agent | M | BACKLOG |
| [025](ticket-025-knowledge-agent.md) | Knowledge Agent | L | BACKLOG |
| [026](ticket-026-approval-agent.md) | Approval Agent | L | BACKLOG |
| [027](ticket-027-analytics-agent.md) | Analytics Agent | M | BACKLOG |
| [028](ticket-028-general-assistant-agent.md) | General Assistant Agent | M | BACKLOG |
| [029](ticket-029-ci-cd-pipeline.md) | CI/CD Pipeline | L | BACKLOG |
| [030](ticket-030-production-hardening.md) | Production Hardening | XL | BACKLOG |

## Complexity Scale

- **S** — Small (< 1 day)
- **M** — Medium (1-3 days)
- **L** — Large (3-5 days)
- **XL** — Extra Large (1-2 weeks)

## GitHub Project Board Mapping

Map these tickets to GitHub Projects columns:
- **Backlog** → BACKLOG tickets
- **Sprint** → TODO + IN_PROGRESS
- **Review** → IN_REVIEW
- **Done** → DONE
