# Quick Start

## 1) Start Backend (Spring Boot)

```powershell
cd spring-backend
mvn spring-boot:run
```

Expected:
- Backend starts on `http://localhost:8084`
- API base is `http://localhost:8084/api/v1`

## 2) Start Frontend (Vite)

```powershell
cd frontend
npm install
npm run dev
```

Expected:
- Frontend starts on `http://localhost:5173`

## 3) Open App

- Frontend: `http://localhost:5173`

## 4) If backend port changes

Update [frontend/src/lib/api.js](frontend/src/lib/api.js) baseURL from:
- `http://localhost:8084/api/v1`

to your new backend URL.
