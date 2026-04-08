# 🚀 How to Run the Project

## ⚠️ IMPORTANT: Follow These Steps Exactly

### Step 1: Stop Any Running Servers
If you have any servers running, stop them first (Ctrl+C in terminals)

### Step 2: Start Backend Server
```bash
cd backend
npm run dev
```

**Wait for this message:**
```
Server running in development mode on port 8084
MongoDB Connected: ...
```

### Step 3: Start Frontend Server (New Terminal)
```bash
cd frontend
npm run dev
```

**Wait for this message:**
```
VITE v5.4.21  ready in XXX ms
➜  Local:   http://localhost:5173/
```

### Step 4: Open Browser
Go to: **http://localhost:5173**

---

## ✅ What You Should See

1. **Beautiful landing page** with:
   - Glass navbar at top
   - Animated floating orbs in background
   - Gradient text "Donate Hope, Transform Lives"
   - Glass cards with stats
   - Smooth animations

2. **No errors** in browser console (F12)

---

## 🐛 If You See a Blank Page

### Quick Fix:
1. **Hard refresh** the browser: `Ctrl + Shift + R` (Windows) or `Cmd + Shift + R` (Mac)
2. **Clear cache**: 
   - Press F12
   - Right-click refresh button
   - Select "Empty Cache and Hard Reload"

### If Still Blank:
1. Open browser console (F12)
2. Look for errors
3. Check if both servers are running
4. Verify URLs:
   - Backend: http://localhost:8084
   - Frontend: http://localhost:5173

---

## 🔍 Troubleshooting

### Error: "Port already in use"
```bash
# Windows - Kill process on port 8084
netstat -ano | findstr :8084
taskkill /PID <PID_NUMBER> /F

# Or change port in backend/.env
PORT=8085
```

### Error: "Cannot find module"
```bash
# Reinstall dependencies
cd frontend
rm -rf node_modules
npm install

cd ../backend
rm -rf node_modules
npm install
```

### Error: "MongoDB connection failed"
- Check internet connection
- Verify MongoDB Atlas is accessible
- Check connection string in `backend/.env`

---

## ✅ Verification Checklist

Before opening browser, verify:
- [ ] Backend terminal shows "Server running"
- [ ] Backend terminal shows "MongoDB Connected"
- [ ] Frontend terminal shows "Local: http://localhost:5173/"
- [ ] No error messages in either terminal

---

## 🎨 What to Test

1. **Landing Page**
   - See glassmorphism effects
   - Watch floating animations
   - Scroll through sections

2. **Register/Login**
   - Click "Start Donating"
   - Choose a role (Donor, Recipient, Logistics, Admin)
   - Fill in details
   - Create account

3. **Dashboard**
   - See role-specific dashboard
   - Glass cards everywhere
   - Smooth transitions

4. **Chatbot**
   - Click chat icon (bottom right)
   - Send a message
   - See glass chat interface

---

## 📞 Still Having Issues?

1. Check `TROUBLESHOOTING.md` for detailed solutions
2. Verify Node.js version: `node --version` (should be v16+)
3. Check npm version: `npm --version` (should be v8+)
4. Ensure MongoDB Atlas is accessible

---

## 🎉 Success!

If you see the beautiful glassmorphism landing page with animated orbs and gradient text, **congratulations!** The project is running successfully.

Now you can:
- Register new users
- Test all 4 role dashboards
- Create donations
- Submit requests
- Coordinate deliveries
- Chat with AI assistant

Enjoy! 🚀
