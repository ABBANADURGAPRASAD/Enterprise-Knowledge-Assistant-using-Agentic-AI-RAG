# RAG Documents

Sample documents for testing the RAG ingestion pipeline.

## Folder Structure

```
rag-documents/
├── hr/              # HR policies, employee handbook
├── policies/        # Company policies, code of conduct
├── projects/        # Project documentation
├── technical/       # Technical docs, architecture guides
├── sops/            # Standard Operating Procedures
└── knowledge-base/  # General knowledge articles
```

## Supported Formats

PDF, DOCX, TXT, Markdown (.md), HTML

## Classification Guidelines

| Folder | Recommended Classification |
|--------|---------------------------|
| hr/ | CONFIDENTIAL or INTERNAL |
| policies/ | INTERNAL |
| projects/ | INTERNAL |
| technical/ | INTERNAL |
| sops/ | INTERNAL |
| knowledge-base/ | PUBLIC or INTERNAL |

## Upload

```bash
curl -X POST http://localhost:8080/api/v1/documents/upload \
  -H "Authorization: Bearer $TOKEN" \
  -F "file=@rag-documents/hr/employee-handbook.pdf" \
  -F "documentType=HR" \
  -F "classification=INTERNAL"
```

Place your company documents in the appropriate subfolder before ingestion.
