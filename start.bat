@echo off
echo ========================================
echo   GiveHope - Donation Management System
echo   Starting Application...
echo ========================================
echo.

echo [1/2] Starting Backend Server...
start "GiveHope Backend" cmd /k "cd spring-backend && mvn spring-boot:run"

timeout /t 3 /nobreak > nul

echo [2/2] Starting Frontend Server...
start "GiveHope Frontend" cmd /k "cd frontend && npm run dev"

echo.
echo ========================================
echo   Application Started Successfully!
echo ========================================
echo.
echo Backend:  http://localhost:8084/api/v1
echo Frontend: http://localhost:5173
echo.
echo Press any key to exit this window...
pause > nul
