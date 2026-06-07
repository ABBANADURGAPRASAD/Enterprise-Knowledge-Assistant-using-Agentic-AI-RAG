# Ticket 004: JWT Security

| Field           |    Value |
|-----------------|----------|
| **ID**           | EKA-004 |
| **Title**       | JWT Security |
| **Complexity** | L |
| **Dependencies** | 003 |
| **Status**      | BACKLOG |
| **Assignee**    | _Unassigned_ |
| **Sprint**       _TBD_ |

## Objective

Implement JWT-based authentication and Spring Security configuration with role-based authorization.

## Requirements

- [ ] Implement JwtTokenProvider for token generation/validation
- [ ] Create JwtAuthenticationFilter
- [ ] Configure SecurityFilterChain with stateless JWT
- [ ] Implement AuthController (login, refresh, logout)
- [ ] Create UserDetailsService with role loading
- [ ] Add method-level @PreAuthorize annotations

## Acceptance Criteria

- [ ] Login returns JWT access + refresh tokens
- [ ] Protected endpoints reject unauthenticated requests
- [ ] Role-based access enforced on endpoints
- [ ] Token refresh works without re-login
- [ ] Password encoding with BCrypt

## Technical Design

Stateless JWT. Access token 15min, refresh token 7 days. Roles stored in JWT claims.

## API Requirements

POST /api/v1/auth/login, POST /api/v1/auth/refresh, POST /api/v1/auth/logout, GET /api/v1/auth/me

## Database Changes

users, roles, user_roles tables

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
