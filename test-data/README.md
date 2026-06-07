# Test Data

Test fixtures for development and integration testing.

## Contents

```
test-data/
├── users/           # Sample user accounts per role
├── documents/       # Sample documents for RAG testing
└── workflows/       # Sample workflow instances
```

## Sample Users

| Username | Password | Role |
|----------|----------|------|
| ceo | password | CEO |
| hr.admin | password | HR |
| manager1 | password | MANAGER |
| employee1 | password | EMPLOYEE |
| external | password | EXTERNAL |

> Note: Seed these via Flyway migration V2__seed_data.sql when implementing ticket-004.

## Usage

Import test data during local development to validate RBAC, RAG security, and agent routing across all roles.
