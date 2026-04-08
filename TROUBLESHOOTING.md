# 🔧 Troubleshooting Guide

## Common Issues and Solutions

### 🚫 Backend Issues

#### Issue 1: "Port 8084 is already in use"
**Symptoms**: Backend won't start, error about port in use

**Solutions**:
```bash
# Option 1: Kill the process using the port
# Windows
netstat -ano | findstr :8084
taskkill /PID <PID_NUMBER> /F

# Option 2: Change the port
# Edit backend/.env
PORT=8085

# Then update frontend/src/lib/api.js
baseURL: "http://localhost:8085/api/v1"
```

#### Issue 2: "MongoDB connection failed"
**Symptoms**: Backend starts but shows MongoDB connection error

**Solutions**:
1. **Check MongoDB Atlas**:
   - Verify network access (allow your IP)
   - Check database user credentials
   - Ensure cluster is running

2. **Check connection string**:
   ```env
   # In backend/.env
   MONGODB_URI=mongodb+srv://username:password@cluster.mongodb.net/dbname
   ```

3. **Use local MongoDB**:
   ```env
   MONGODB_URI=mongodb://localhost:27017/givehope
   ```

#### Issue 3: "Module not found"
**Symptoms**: Backend crashes with module errors

**Solution**:
```bash
cd backend
rm -rf node_modules package-lock.json
npm install
```

### 🎨 Frontend Issues

#### Issue 4: "Cannot GET /"
**Symptoms**: Blank page or 404 error

**Solutions**:
1. **Check if Vite is running**:
   ```bash
   cd frontend
   npm run dev
   ```

2. **Clear browser cache**:
   - Press Ctrl+Shift+Delete
   - Clear cached images and files

3. **Check port**:
   - Ensure http://localhost:5173 is accessible

#### Issue 5: "Glass effects not showing"
**Symptoms**: UI looks flat, no blur effects

**Solutions**:
1. **Check browser support**:
   - Use Chrome, Firefox, Safari, or Edge (latest versions)
   - Backdrop-filter not supported in IE

2. **Check CSS loading**:
   - Open DevTools (F12)
   - Check if index.css is loaded
   - Look for CSS errors in console

3. **Force reload**:
   - Press Ctrl+Shift+R (hard reload)

#### Issue 6: "White screen / Blank page"
**Symptoms**: Nothing renders, white screen

**Solutions**:
1. **Check console errors**:
   - Open DevTools (F12)
   - Look for JavaScript errors
   - Check Network tab for failed requests

2. **Verify dependencies**:
   ```bash
   cd frontend
   npm install
   ```

3. **Check API connection**:
   - Ensure backend is running
   - Check API URL in lib/api.js

### 🔐 Authentication Issues

#### Issue 7: "Login not working"
**Symptoms**: Can't log in, error messages

**Solutions**:
1. **Check credentials**:
   - Verify email and password
   - Check for typos

2. **Check backend logs**:
   - Look at backend terminal
   - Check for authentication errors

3. **Clear localStorage**:
   ```javascript
   // In browser console
   localStorage.clear()
   ```

4. **Verify JWT_SECRET**:
   ```env
   # In backend/.env
   JWT_SECRET=your_secret_key_here
   ```

#### Issue 8: "Token expired"
**Symptoms**: Logged out automatically

**Solution**:
- This is normal behavior (24h expiration)
- Simply log in again
- To change expiration:
  ```env
  # In backend/.env
  JWT_EXPIRE=7d  # 7 days
  ```

### 📱 UI/UX Issues

#### Issue 9: "Layout broken on mobile"
**Symptoms**: Elements overlapping, wrong sizes

**Solutions**:
1. **Clear cache**:
   - Hard reload (Ctrl+Shift+R)

2. **Check viewport**:
   - Ensure meta viewport tag in index.html

3. **Test in DevTools**:
   - Open DevTools (F12)
   - Toggle device toolbar (Ctrl+Shift+M)
   - Test different screen sizes

#### Issue 10: "Animations not smooth"
**Symptoms**: Laggy animations, poor performance

**Solutions**:
1. **Check hardware acceleration**:
   - Enable in browser settings
   - Chrome: chrome://settings → Advanced → System

2. **Reduce blur intensity**:
   ```css
   /* In index.css */
   --glass-blur: blur(8px);  /* Reduce from 12px */
   ```

3. **Disable animations**:
   ```css
   /* Temporarily disable for testing */
   * {
     animation: none !important;
   }
   ```

### 🔄 API Issues

#### Issue 11: "CORS error"
**Symptoms**: API calls blocked, CORS errors in console

**Solutions**:
1. **Check backend CORS**:
   ```javascript
   // In backend/src/index.js
   app.use(cors());
   ```

2. **Check API URL**:
   ```javascript
   // In frontend/src/lib/api.js
   baseURL: "http://localhost:8084/api/v1"
   ```

3. **Restart backend**:
   ```bash
   # Stop and restart backend server
   ```

#### Issue 12: "404 on API calls"
**Symptoms**: All API calls return 404

**Solutions**:
1. **Check API version**:
   - Frontend expects: `/api/v1/...`
   - Backend provides: `/api/v1/...`

2. **Verify routes**:
   ```bash
   # Test backend directly
   curl http://localhost:8084/api/v1/auth/login
   ```

### 💾 Database Issues

#### Issue 13: "Data not saving"
**Symptoms**: Forms submit but data doesn't persist

**Solutions**:
1. **Check MongoDB connection**:
   - Look at backend logs
   - Verify connection string

2. **Check model schemas**:
   - Ensure all required fields are provided

3. **Check backend logs**:
   - Look for validation errors
   - Check for database errors

#### Issue 14: "Duplicate key error"
**Symptoms**: Can't create user, duplicate error

**Solution**:
- Email already exists
- Use different email
- Or delete existing user from database

### 🎨 Styling Issues

#### Issue 15: "Tailwind classes not working"
**Symptoms**: Custom classes have no effect

**Solutions**:
1. **Check Tailwind config**:
   ```javascript
   // tailwind.config.js
   content: [
     "./index.html",
     "./src/**/*.{js,ts,jsx,tsx}",
   ]
   ```

2. **Restart dev server**:
   ```bash
   # Stop and restart frontend
   ```

3. **Check class names**:
   - Ensure no typos
   - Check if class exists in Tailwind

#### Issue 16: "Custom CSS not loading"
**Symptoms**: Glassmorphism styles missing

**Solutions**:
1. **Check import**:
   ```javascript
   // In main.jsx
   import './assets/css/index.css'
   ```

2. **Check file path**:
   - Verify index.css exists
   - Check file location

3. **Clear Vite cache**:
   ```bash
   cd frontend
   rm -rf node_modules/.vite
   npm run dev
   ```

### 🔍 Debugging Tips

#### General Debugging Process
1. **Check browser console** (F12)
   - Look for errors
   - Check network requests
   - Verify API responses

2. **Check backend logs**
   - Look at terminal output
   - Check for errors
   - Verify requests received

3. **Test API directly**
   - Use Postman or curl
   - Test endpoints individually
   - Verify responses

4. **Isolate the issue**
   - Test one feature at a time
   - Disable other features
   - Narrow down the problem

#### Useful Commands

```bash
# Check if port is in use
netstat -ano | findstr :8084

# Kill process by PID
taskkill /PID <PID> /F

# Clear npm cache
npm cache clean --force

# Reinstall dependencies
rm -rf node_modules package-lock.json
npm install

# Check Node version
node --version

# Check npm version
npm --version

# Test backend endpoint
curl http://localhost:8084/api/v1/auth/login

# View backend logs
cd backend && npm run dev

# View frontend logs
cd frontend && npm run dev
```

### 📞 Still Having Issues?

If none of these solutions work:

1. **Check the logs**:
   - Backend terminal output
   - Frontend terminal output
   - Browser console (F12)

2. **Verify environment**:
   - Node.js version (v16+)
   - npm version (v8+)
   - MongoDB connection

3. **Start fresh**:
   ```bash
   # Backend
   cd backend
   rm -rf node_modules
   npm install
   npm run dev

   # Frontend
   cd frontend
   rm -rf node_modules
   npm install
   npm run dev
   ```

4. **Check documentation**:
   - README.md
   - QUICK_START.md
   - PROJECT_SUMMARY.md

### ✅ Verification Checklist

Before asking for help, verify:
- [ ] Node.js is installed (v16+)
- [ ] MongoDB is accessible
- [ ] Backend is running (port 8084)
- [ ] Frontend is running (port 5173)
- [ ] No console errors
- [ ] API calls are successful
- [ ] Environment variables are set
- [ ] Dependencies are installed

### 🎯 Quick Fixes

**90% of issues are solved by**:
1. Restarting both servers
2. Clearing browser cache
3. Reinstalling dependencies
4. Checking environment variables
5. Verifying MongoDB connection

---

## 🆘 Emergency Reset

If everything is broken:

```bash
# 1. Stop all servers
# Press Ctrl+C in both terminals

# 2. Clean everything
cd backend
rm -rf node_modules package-lock.json
cd ../frontend
rm -rf node_modules package-lock.json

# 3. Reinstall
cd ../backend
npm install
cd ../frontend
npm install

# 4. Restart
# Terminal 1
cd backend && npm run dev

# Terminal 2
cd frontend && npm run dev
```

This should fix 99% of issues!
