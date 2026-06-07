# Ticket 010: Recruitment Agent

| Field           |    Value |
|-----------------|----------|
| **ID**          | EKA-010 |
| **Title**       | Recruitment Agent |
| **Complexity** | L |
| **Dependencies** | 007,011 |
| **Status**      | BACKLOG |
| **Assignee**    | _Unassigned_ |
| **Sprint**       _TBD_ |

## Objective

Implement Recruitment Agent for job postings, applications, candidate screening, and interview scheduling.

## Requirements

- [ ] Recruitment prompt for HR and external users
- [ ] Tools: job search, application submit, candidate lookup
- [ ] External user access to public job listings
- [ ] Application status tracking
- [ ] RAG over job descriptions and requirements

## Acceptance Criteria

- [ ] External users can search and apply for jobs
- [ ] HR can manage recruitment pipeline via chat
- [ ] Job application component triggered dynamically
- [ ] Candidate data protected from unauthorized access

## Technical Design

Dual access: HR full pipeline, External limited to public jobs.

## API Requirements

Via Agent Router + GET /api/v1/jobs, POST /api/v1/applications

## Database Changes

jobs, applications, candidates tables

## AI Requirements

Recruitment prompt with job context

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
