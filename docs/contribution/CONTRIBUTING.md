# Contributing to Enterprise Knowledge Assistant

Thank you for contributing! Please follow these guidelines.

## Getting Started

1. Read [LOCAL_SETUP.md](../onboarding/LOCAL_SETUP.md)
2. Review [Coding Standards](../coding-standards/README.md)
3. Check [ToDo/](../../ToDo/) for available tickets

## Branching Strategy

```
main          ← production releases
develop       ← integration branch
feature/*     ← new features (from develop)
fix/*         ← bug fixes (from develop)
release/*     ← release preparation
hotfix/*      ← production hotfixes (from main)
```

## Workflow

1. Pick a ticket from `ToDo/` and set status to `IN_PROGRESS`
2. Create branch: `feature/EKA-007-agent-router`
3. Implement with tests
4. Update ticket status to `IN_REVIEW`
5. Open Pull Request against `develop`
6. Address review feedback
7. Merge and set ticket to `DONE`

## Pull Request Checklist

- [ ] Code follows coding standards
- [ ] Unit tests added/updated
- [ ] No secrets committed
- [ ] README updated if needed
- [ ] Ticket reference in PR description

## Code Review

- At least one approval required
- CI must pass (build + tests)
- No merge conflicts

## Reporting Issues

Use GitHub Issues with labels: `bug`, `enhancement`, `security`, `documentation`
