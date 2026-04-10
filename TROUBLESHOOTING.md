# Troubleshooting

## Backend does not start

1. Check Java version:

```powershell
java -version
```

Use Java 25.

2. Run Maven clean test:

```powershell
cd spring-backend
mvn clean test
```

3. If port conflict on 8084, change backend port in Spring config and match frontend API URL in [frontend/src/lib/api.js](frontend/src/lib/api.js).

## Frontend cannot connect to backend

- Confirm backend is running at `http://localhost:8084`
- Confirm frontend base URL is `http://localhost:8084/api/v1` in [frontend/src/lib/api.js](frontend/src/lib/api.js)

## Frontend build issues

```powershell
cd frontend
npm install
npm run build
```

## MongoDB connection issues

- Verify MongoDB URI in Spring backend configuration
- Ensure Atlas/local MongoDB is reachable from your machine
