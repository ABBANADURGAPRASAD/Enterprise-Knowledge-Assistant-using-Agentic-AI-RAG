# API Documentation

REST API reference for the Enterprise Knowledge Assistant.

Base URL: `http://localhost:8080/api/v1`

## Authentication

All endpoints except `/auth/login` and `/public/**` require JWT Bearer token.

```
Authorization: Bearer <access_token>
```

### POST /auth/login

Authenticate and receive tokens.

**Request:**
```json
{ "username": "admin", "password": "password" }
```

**Response:**
```json
{
  "success": true,
  "data": {
    "accessToken": "eyJ...",
    "refreshToken": "eyJ...",
    "tokenType": "Bearer",
    "expiresIn": 900,
    "user": { "id": 1, "username": "admin", "roles": ["CEO"] }
  }
}
```

### GET /auth/me

Get current authenticated user.

## Chat

### POST /chat/message

Send a message to the AI assistant.

**Request:**
```json
{ "message": "Show pending approvals", "conversationId": 1 }
```

**Response:**
```json
{
  "success": true,
  "data": {
    "conversationId": 1,
    "message": "You have 2 pending approvals...",
    "agentUsed": "APPROVAL_AGENT",
    "confidence": 0.85,
    "actionPayload": {
      "actionType": "OPEN_COMPONENT",
      "componentName": "ApprovalListComponent",
      "displayMode": "SIDE_PANEL",
      "params": { "status": "PENDING" }
    }
  }
}
```

## Documents

### POST /documents/upload

Upload and ingest a document for RAG.

**Form Data:**
- `file` — Document file (PDF, DOCX, TXT, MD)
- `documentType` — HR, POLICY, TECHNICAL, SOP, GENERAL
- `classification` — PUBLIC, INTERNAL, CONFIDENTIAL, RESTRICTED

## RAG

### POST /rag/query

Direct RAG query (bypasses agent router).

**Request:**
```json
{ "query": "What is the leave policy?", "topK": 5 }
```

## Search

### GET /search

Enterprise unified search.

```
GET /search?q=leave+policy&type=POLICY&department=HR
```

## Analytics

### GET /analytics/dashboard

Dashboard KPIs (CEO, HR, Manager only).

## Swagger UI

Interactive API docs: `http://localhost:8080/swagger-ui.html`

## Postman Collection

Import [postman/EKA-API.postman_collection.json](../postman/EKA-API.postman_collection.json)
