# Ticket 024: Employee Agent

| Field           |    Value |
|-----------------|----------|
| **ID**          | EKA-024 |
| **Title**       | Employee Agent |
| **Complexity** | M |
| **Dependencies** | 007,011 |
| **Status**      | BACKLOG |
| **Assignee**    | _Unassigned_ |
| **Sprint**       _TBD_ |

## Objective

Implement Employee Agent for personal info, projects, achievements, and self-service queries.

## Requirements

- [ ] Employee-specific prompt with personal context
- [ ] Tools: personal profile, leave balance, project lookup, policy search
- [ ] Access limited to own data only
- [ ] Self-service leave application
- [ ] Achievement and performance view

## Acceptance Criteria

- [ ] Employee sees only personal data
- [ ] Leave balance query returns accurate data
- [ ] Self-service actions work via tool calling
- [ ] Policy search returns employee-accessible docs

## Technical Design

Employee context injected from authenticated user profile.

## API Requirements

Via Agent Router

## Database Changes

Uses employees, leave_balances, projects

## AI Requirements

Employee self-service prompt

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
