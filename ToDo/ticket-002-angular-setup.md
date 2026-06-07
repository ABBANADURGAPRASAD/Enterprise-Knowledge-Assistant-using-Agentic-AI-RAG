# Ticket 002: Angular Setup

| Field           |    Value |
|-----------------|----------|
| **ID**          | EKA-002 |
| **Title**       | Angular Setup |
| **Complexity** | M |
| **Dependencies** | 001 |
| **Status**      | BACKLOG |
| **Assignee**    | _Unassigned_ |
| **Sprint**       _TBD_ |

## Objective

Initialize Angular 20+ SPA with enterprise module structure, routing, theming, and core services.

## Requirements

- [ ] Initialize Angular 20+ project with standalone components
- [ ] Configure module structure (core, shared, features, layouts)
- [ ] Set up Angular Material and theming
- [ ] Configure environment files (dev, staging, prod)
- [ ] Implement HTTP interceptor skeleton
- [ ] Configure lazy-loaded feature routes

## Acceptance Criteria

- [ ] Angular app builds and serves on localhost:4200
- [ ] Core/shared/features folder structure in place
- [ ] Environment configs for dev/staging/prod
- [ ] Material theme applied globally
- [ ] Lazy routing configured for all feature modules

## Technical Design

Angular 20 standalone components. Feature-based lazy loading. Core module for singletons.

## API Requirements

GET /api/v1/health (proxy config)

## Database Changes

_No database changes_

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
