# Scripts

Utility scripts for setup, build, deployment, and database migration.

## Available Scripts

| Script                                       | Purpose                          |
|----------------------------------------------|----------------------------------|
| [setup/setup-local.sh](setup/setup-local.sh) | Initialize local dev environment |
| build/build-all.sh                           | Build backend + frontend         |
| deploy/deploy-staging.sh                     | Deploy to staging                |
| migrate/run-migrations.sh                    | Run database migrations          |

## Usage

```bash
chmod +x scripts/setup/setup-local.sh
./scripts/setup/setup-local.sh
```

All scripts should be run from the project root directory.
