# Enterprise Knowledge Assistant using Agentic AI & RAG

[![Java 21](https://img.shields.io/badge/Java-21-orange)](https://openjdk.org/)
[![Spring Boot 3.x](https://img.shields.io/badge/Spring%20Boot-3.x-green)](https://spring.io/projects/spring-boot)
[![Angular 20+](https://img.shields.io/badge/Angular-20+-red)](https://angular.dev/)
[![Spring AI](https://img.shields.io/badge/Spring%20AI-1.x-blue)](https://spring.io/projects/spring-ai)
[![License](https://img.shields.io/badge/License-Enterprise-blue)](LICENSE)

Production-grade enterprise knowledge assistant combining **Agentic AI**, **Retrieval-Augmented Generation (RAG)**, and **role-based access control** for secure, intelligent organizational assistance.

## Overview

The Enterprise Knowledge Assistant (EKA) enables employees, managers, HR, and external users to interact with company knowledge through a ChatGPT-style conversational interface. Multiple specialized AI agents handle domain-specific requests while a central **Agent Router** performs intent classification, security validation, and tool selection.

### Key Capabilities

- **Multi-Agent Architecture** — HR, Manager, Employee, Recruitment, Knowledge, Approval, Analytics, and General Assistant agents
- **Enterprise RAG** — Document ingestion, chunking, embedding, vector search, reranking, and role-filtered retrieval
- **Tool Calling** — AI agents invoke backend services (leave, approvals, recruitment, analytics, document search)
- **Dynamic UI** — Angular components load automatically based on AI action payloads
- **Workflow Engine** — Leave, permission, reimbursement, complaint, onboarding, recruitment, and project workflows
- **Security** — JWT authentication, RBAC, RAG security filters, prompt injection protection, PII masking, audit logging

## Technology Stack

| Layer | Technology |
|-------|------------|
| Backend | Java 21, Spring Boot 3.x, Spring Security, Spring AI |
| Frontend | Angular 20+, TypeScript, RxJS, Angular Material |
| AI/LLM | OpenAI GPT-4 / Google Gemini via Spring AI |
| Database | MySQL 8.x |
| Vector Store | ChromaDB / PGVector |
| Messaging | WebSocket (STOMP) |
| Containerization | Docker, Docker Compose |
| CI/CD | GitHub Actions |

## Project Structure

```
Enterprise-Knowledge-Assistant-using-Agentic-AI-RAG/
├── backend/                 # Spring Boot application (clean architecture)
├── frontend/                # Angular SPA
├── docs/                    # API docs, onboarding, coding standards
├── architecture/            # Architecture diagrams, ADRs, sequence diagrams
├── database/                # Schemas, migrations, seed data
├── deployment/              # K8s, Helm, Nginx configs
├── docker/                  # Dockerfiles and compose services
├── postman/                 # API collections
├── prompts/                 # AI prompt templates
├── rag-documents/           # Sample documents for RAG ingestion
├── vector-store/            # Vector store configuration and backups
├── test-data/               # Test fixtures
├── scripts/                 # Setup, build, deploy scripts
├── assets/                  # Diagrams, logos, screenshots
└── ToDo/                    # Project management tickets
```

## Quick Start

### Prerequisites

- Java 21 JDK
- Node.js 20+ and npm
- Docker & Docker Compose
- MySQL 8.x (or use Docker)
- OpenAI API key or Google Gemini API key

### Local Development

```bash
# 1. Clone and configure
git clone <repository-url>
cd Enterprise-Knowledge-Assistant-using-Agentic-AI-RAG
cp backend/src/main/resources/application-dev.yml.example backend/src/main/resources/application-dev.yml

# 2. Start infrastructure
docker compose -f docker/docker-compose.yml up -d mysql chroma

# 3. Start backend
cd backend && ./mvnw spring-boot:run -Dspring-boot.run.profiles=dev

# 4. Start frontend
cd frontend && npm install && npm start
```

Access the application at `http://localhost:4200`. API docs at `http://localhost:8080/swagger-ui.html`.

See [docs/onboarding/LOCAL_SETUP.md](docs/onboarding/LOCAL_SETUP.md) for detailed setup instructions.

## Architecture

```
┌─────────────┐     ┌──────────────┐     ┌─────────────────┐
│   Angular   │────▶│  Spring Boot │────▶│   Agent Router  │
│   Frontend  │◀────│     API      │◀────│  (Intent + RBAC)│
└─────────────┘     └──────┬───────┘     └────────┬────────┘
                           │                       │
                    ┌──────┴───────┐        ┌──────┴──────┐
                    │    MySQL     │        │   Agents    │
                    └──────────────┘        │ HR|Mgr|Rec..│
                           │                └──────┬──────┘
                    ┌──────┴───────┐              │
                    │  ChromaDB /  │◀─────────────┘
                    │   PGVector   │        Tool Calling
                    └──────────────┘
```

See [architecture/README.md](architecture/README.md) for detailed architecture documentation.

## Roles & Access

| Role          | Access Scope                                     |
|---------------|--------------------------------------------------|
| CEO           | Organization-wide information                    |
| HR            | Employees, payroll, hiring, complaints, policies |
| Manager       | Team information, approvals, tasks               |
| Employee      | Personal info, projects, achievements, policies  |
| External User | Job opportunities, company info, recruitment     |

## Documentation Index

- [Architecture Overview](architecture/README.md)
- [API Documentation](docs/api/README.md)
- [Database Schema](database/README.md)
- [Local Setup Guide](docs/onboarding/LOCAL_SETUP.md)
- [Coding Standards](docs/coding-standards/README.md)
- [Contribution Guidelines](docs/contribution/CONTRIBUTING.md)
- [Deployment Guide](deployment/README.md)
- [Project Tickets](ToDo/README.md)

## Project Management

All development tasks are tracked in the [ToDo/](ToDo/) folder as numbered tickets. See [ToDo/README.md](ToDo/README.md) for the project board structure and ticket workflow.

## License

Proprietary — Enterprise Internal Use Only.
