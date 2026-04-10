# GiveHope - Donation Management System

Full-stack donation management platform with:
- Spring Boot backend ([spring-backend](spring-backend))
- React + Vite frontend ([frontend](frontend))

## Tech Stack

### Backend
- Java 25
- Spring Boot 4.0.5
- Spring Security
- Spring Data MongoDB

### Frontend
- React 18
- Vite 5
- TailwindCSS
- Axios

## Prerequisites

- JDK 25
- Maven 3.9+
- Node.js 18+
- MongoDB (local or Atlas)

## Run Locally

### 1. Start backend (Eclipse or terminal)

From terminal:

```powershell
cd spring-backend
mvn spring-boot:run
```

Backend base URL:
- `http://localhost:8084/api/v1`

### 2. Start frontend (VS Code terminal)

```powershell
cd frontend
npm install
npm run dev
```

Frontend URL:
- `http://localhost:5173`

## Frontend API Configuration

Frontend API client is in [frontend/src/lib/api.js](frontend/src/lib/api.js).
For development it calls:
- `http://localhost:8084/api/v1`

If you change backend port, update that file accordingly.

## Project Structure

```text
FSAD-PS04-main/
  frontend/
  spring-backend/
  README.md
  QUICK_START.md
  RUN_PROJECT.md
  TROUBLESHOOTING.md
  start.bat
```

## Notes

- The old Node/Express backend folder has been retired in favor of Spring Boot.
- Use [spring-backend](spring-backend) as the only backend service.
