# Ticket 017: Document Ingestion

| Field           |    Value |
|-----------------|----------|
| **ID**          | EKA-017 |
| **Title**       | Document Ingestion |
| **Complexity** | L |
| **Dependencies** | 005,006 |
| **Status**      | BACKLOG |
| **Assignee**    | _Unassigned_ |
| **Sprint**       _TBD_ |

## Objective

Build document ingestion pipeline with parsing, metadata extraction, and batch processing.

## Requirements

- [ ] Multi-format parser (PDF, DOCX, TXT, MD, HTML)
- [ ] Metadata extraction (title, author, department, classification)
- [ ] Batch upload API with progress tracking
- [ ] Scheduled re-ingestion for updated documents
- [ ] Ingestion status dashboard
- [ ] Duplicate detection

## Acceptance Criteria

- [ ] PDF and DOCX parsed correctly
- [ ] Metadata auto-extracted and editable
- [ ] Batch upload of 100 docs completes
- [ ] Ingestion errors reported with details
- [ ] Duplicate documents detected and skipped

## Technical Design

Async ingestion via @Async or message queue. Status tracked in document_ingestion_jobs.

## API Requirements

POST /api/v1/documents/upload, POST /api/v1/documents/batch, GET /api/v1/documents/ingestion/{jobId}/status

## Database Changes

document_ingestion_jobs table

## AI Requirements

None — uses embedding service

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
