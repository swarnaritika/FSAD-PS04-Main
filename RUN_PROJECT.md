# How To Run The Project

## Backend (Spring Boot)

Run in Eclipse:
- Import [spring-backend](spring-backend) as Maven project
- Set project JDK to Java 25
- Run as Spring Boot App

Or run from terminal:

```powershell
cd spring-backend
mvn spring-boot:run
```

## Frontend (VS Code)

```powershell
cd frontend
npm install
npm run dev
```

## URLs

- Frontend: `http://localhost:5173`
- Backend API: `http://localhost:8084/api/v1`

## Verify backend quickly

```powershell
curl http://localhost:8084/api/v1/stats
```
