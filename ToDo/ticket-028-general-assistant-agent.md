# Ticket 028: General Assistant Agent

| Field           |    Value |
|-----------------|----------|
| **ID**          | EKA-028 |
| **Title**       | General Assistant Agent |
| **Complexity** | M |
| **Dependencies** | 007 |
| **Status**      | BACKLOG |
| **Assignee**    | _Unassigned_ |
| **Sprint**       _TBD_ |

## Objective

Implement General Assistant as fallback agent for greetings, general queries, and navigation help.

## Requirements

- [ ] General-purpose conversational prompt
- [ ] Application navigation assistance
- [ ] Fallback when no specialized agent matches
- [ ] FAQ responses
- [ ] Polite decline for out-of-scope requests

## Acceptance Criteria

- [ ] Greets users appropriately
- [ ] Provides app navigation help
- [ ] Handles unknown intents gracefully
- [ ] Never accesses restricted data

## Technical Design

Default agent in router fallback chain. Minimal tool access.

## API Requirements

Via Agent Router

## Database Changes

_No database changes_

## AI Requirements

General assistant system prompt

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
