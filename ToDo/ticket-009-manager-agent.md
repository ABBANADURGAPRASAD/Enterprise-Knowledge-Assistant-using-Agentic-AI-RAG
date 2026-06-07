# Ticket 009: Manager Agent

| Field           |    Value |
|-----------------|----------|
| **ID**          | EKA-009 |
| **Title**       | Manager Agent |
| **Complexity** | L |
| **Dependencies** | 007,011 |
| **Status**      | BACKLOG |
| **Assignee**    | _Unassigned_ |
| **Sprint**       _TBD_ |

## Objective

Implement Manager Agent for team management, approvals, performance, and task delegation.

## Requirements

- [ ] Manager-specific prompt and team context injection
- [ ] Tools: team lookup, approval actions, task assignment
- [ ] Access limited to manager's direct reports
- [ ] Pending approvals summary and actions
- [ ] Performance review assistance

## Acceptance Criteria

- [ ] Manager sees only their team's data
- [ ] Approval actions execute via tool calling
- [ ] Team summary generated correctly
- [ ] Manager role required for access

## Technical Design

Manager context includes team member IDs from org hierarchy.

## API Requirements

Via Agent Router

## Database Changes

Uses employees, approvals, projects tables

## AI Requirements

Manager prompt with team context template

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
