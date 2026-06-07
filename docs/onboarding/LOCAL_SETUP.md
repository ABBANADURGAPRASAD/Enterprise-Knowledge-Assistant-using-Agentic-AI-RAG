# Local Setup Guide

Step-by-step guide to run the Enterprise Knowledge Assistant locally.

## Prerequisites

| Tool           | Version | Check                                              |
|----------------|---------|----------------------------------------------------|
| Java JDK       | 21+     | `java -version`                                    |
| Maven          | 3.9+    | `mvn -version`                                     |
| Node.js        | 20+     | `node -version`                                    |
| Docker         | 24+     | `docker --version`                                 |
| OpenAI API Key | —       | [platform.openai.com](https://platform.openai.com) |

## Step 1: Clone Repository

```bash
git clone <repository-url>
cd Enterprise-Knowledge-Assistant-using-Agentic-AI-RAG
```

## Step 2: Start Infrastructure

```bash
docker compose -f docker/docker-compose.yml up -d mysql chroma
```

Wait for health checks to pass (~30 seconds).

## Step 3: Configure Backend

```bash
export OPENAI_API_KEY=sk-your-key
export JWT_SECRET=dev-secret-key-minimum-256-bits-for-hs256-algorithm
```

## Step 4: Start Backend

```bash
cd backend
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

Verify: `curl http://localhost:8080/actuator/health`

## Step 5: Start Frontend

```bash
cd frontend
npm install
npm start
```

Open `http://localhost:4200`

## Step 6: Upload Sample Documents

Place documents in `rag-documents/` and upload via API:

```bash
curl -X POST http://localhost:8080/api/v1/documents/upload \
  -H "Authorization: Bearer <token>" \
  -F "file=@rag-documents/hr/employee-handbook.pdf" \
  -F "documentType=HR" \
  -F "classification=INTERNAL"
```

## Troubleshooting

| Issue                    | Solution                                               |
|--------------------------|--------------------------------------------------------|
| MySQL connection refused | Ensure Docker MySQL is running: `docker ps`            |
| ChromaDB unreachable     | Check port 8000: `curl localhost:8000/api/v1/heartbeat`|
| OpenAI errors            | Verify API key and billing status                      |
| CORS errors              | Ensure frontend runs on localhost:4200                 |
