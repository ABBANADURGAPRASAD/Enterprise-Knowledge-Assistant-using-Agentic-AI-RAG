#!/bin/bash
# setup-local.sh — Initialize local development environment

set -e

echo "=== EKA Local Setup ==="

# Check prerequisites
command -v java >/dev/null 2>&1 || { echo "Java 21 required"; exit 1; }
command -v docker >/dev/null 2>&1 || { echo "Docker required"; exit 1; }
command -v node >/dev/null 2>&1 || { echo "Node.js 20+ required"; exit 1; }

# Start infrastructure
echo "Starting MySQL and ChromaDB..."
docker compose -f docker/docker-compose.yml up -d mysql chroma

echo "Waiting for services..."
sleep 15

# Backend
cd backend && mvn clean package -pl backend-app -am -DskipTests 2>/dev/null || mvn clean package -pl backend-app -am -DskipTests
cd ..

# Frontend
echo "Installing frontend dependencies..."
cd frontend && npm install
cd ..

echo ""
echo "=== Setup Complete ==="
echo "1. Set OPENAI_API_KEY environment variable"
echo "2. Start backend:  cd backend && mvn spring-boot:run -pl backend-app -am -Dspring-boot.run.profiles=dev"
echo "3. Start frontend: cd frontend && npm start"
echo "4. Open http://localhost:4200"
