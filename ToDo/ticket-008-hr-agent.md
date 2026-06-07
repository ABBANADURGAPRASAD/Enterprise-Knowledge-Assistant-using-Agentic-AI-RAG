# Ticket 008: HR Agent

| Field           |    Value |
|-----------------|----------|
| **ID**          | EKA-008 |
| **Title**       | HR Agent |
| **Complexity** | L |
| **Dependencies** | 007,011 |
| **Status**      | BACKLOG |
| **Assignee**    | _Unassigned_ |
| **Sprint**       _TBD_ |

## Objective

Implement HR Agent for employee management, policies, complaints, payroll queries, and onboarding.

## Requirements

- [ ] HR-specific system prompt and context
- [ ] Tools: employee lookup, policy search, complaint filing
- [ ] RAG access to HR documents with role filter
- [ ] Handle leave balance, payroll, benefits queries
- [ ] Onboarding workflow integration

## Acceptance Criteria

- [ ] HR Agent responds to employee/policy queries accurately
- [ ] Only HR and CEO roles can invoke HR Agent
- [ ] Document search filtered to HR-accessible docs
- [ ] Tool calls for employee lookup work correctly

## Technical Design

Specialized agent extending BaseAgent. HR prompt template in prompts/agents/hr-agent.st.

## API Requirements

Via Agent Router POST /api/v1/chat/message

## Database Changes

Uses existing HR tables

## AI Requirements

HR system prompt, policy RAG context

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
