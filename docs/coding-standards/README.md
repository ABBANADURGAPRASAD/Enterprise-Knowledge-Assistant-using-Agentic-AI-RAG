# Coding Standards

Enterprise coding standards for the EKA project.

## Java (Backend)

- **Java 21** features encouraged (records, pattern matching, virtual threads)
- **Package naming**: `com.enterprise.eka.<module>`
- **Layer separation**: Controller → Service → Repository (no controller → repository)
- **DTOs** for API input/output, never expose entities directly
- **Lombok** for boilerplate (@Getter, @Setter, @Builder, @RequiredArgsConstructor)
- **MapStruct** for entity ↔ DTO mapping
- **Validation**: Jakarta `@Valid` on request bodies, `@NotBlank`, `@Size`
- **Security**: `@PreAuthorize` on sensitive endpoints
- **Logging**: SLF4J with `@Slf4j`, INFO for business events, DEBUG for dev
- **Tests**: JUnit 5, Mockito, `@SpringBootTest` for integration

## TypeScript (Frontend)

- **Angular 20+** standalone components (no NgModules)
- **Strict TypeScript** — no `any` unless justified
- **Services** for API calls, components for presentation
- **Lazy loading** for all feature routes
- **RxJS** for async operations, avoid nested subscriptions
- **Naming**: PascalCase components, camelCase services/methods
- **File structure**: one component per file, co-located styles

## Git Conventions

- **Branch naming**: `feature/EKA-001-description`, `fix/EKA-042-bug`, `release/v1.0.0`
- **Commit messages**: Conventional Commits (`feat:`, `fix:`, `docs:`, `refactor:`)
- **PR size**: Keep under 400 lines when possible

## API Design

- RESTful URLs: `/api/v1/<resource>`
- Consistent response wrapper: `ApiResponse<T>`
- HTTP status codes: 200 OK, 201 Created, 400 Bad Request, 401 Unauthorized, 403 Forbidden, 404 Not Found, 500 Internal Server Error
- Pagination: `?page=0&size=20&sort=createdAt,desc`

## Security

- Never commit secrets (.env, API keys)
- Input validation on all endpoints
- Parameterized queries (JPA handles this)
- RBAC on every protected resource
