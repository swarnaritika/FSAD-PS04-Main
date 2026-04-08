# 🚀 Quick Start Guide

## Prerequisites Check
- ✅ Node.js installed (v16+)
- ✅ MongoDB running (local or Atlas)
- ✅ npm or yarn installed

## 🎯 Fastest Way to Run

### Windows Users
Simply double-click `start.bat` file in the project root!

### Mac/Linux Users
```bash
# Terminal 1 - Backend
cd backend && npm run dev

# Terminal 2 - Frontend  
cd frontend && npm run dev
```

## 📱 Access the Application

Once both servers are running:
- **Frontend**: http://localhost:5173
- **Backend API**: http://localhost:8084

## 👤 First Time Setup

1. **Register a New Account**
   - Click "Start Donating" or "Sign In"
   - Choose your role (Donor, Recipient, Logistics, or Admin)
   - Fill in your details
   - Click "Create Account"

2. **Explore the Platform**
   - **Donors**: List items for donation
   - **Recipients**: Browse and request items
   - **Logistics**: Coordinate deliveries
   - **Admins**: Create donation drives

## 🎨 UI Features to Try

### Glassmorphism Effects
- Notice the frosted glass cards throughout
- Hover over cards to see the glass-strong effect
- Check out the gradient text and buttons
- Watch the floating background animations

### Interactive Elements
- AI Chatbot (bottom right corner)
- Real-time notifications (bell icon)
- Smooth page transitions
- Responsive design (try resizing your browser)

## 🔧 Common Issues

### Port Already in Use
```bash
# Change backend port in backend/.env
PORT=8085

# Update frontend API URL in frontend/src/lib/api.js
baseURL: "http://localhost:8085/api/v1"
```

### MongoDB Connection Error
- Check if MongoDB is running
- Verify connection string in `backend/.env`
- For Atlas: Check network access settings

### Dependencies Missing
```bash
# Reinstall backend dependencies
cd backend && npm install

# Reinstall frontend dependencies
cd frontend && npm install
```

## 📊 Test Data

### Sample Donation Categories
- Food
- Clothing
- Medical Supplies
- Electronics
- Books
- Other

### Sample Urgency Levels
- Normal
- High
- Emergency

## 🎯 Key Features to Test

1. **Create a Donation** (Donor Role)
   - Go to Dashboard
   - Click "List New Item"
   - Fill in details
   - Submit

2. **Request Support** (Recipient Role)
   - Go to Dashboard
   - Click "New Request"
   - Describe your need
   - Submit

3. **Coordinate Delivery** (Logistics Role)
   - View pending requests
   - Assign delivery to yourself
   - Update delivery status

4. **Create Campaign** (Admin Role)
   - Go to Dashboard
   - Click "New Drive"
   - Set up campaign details
   - Launch

## 💬 AI Chatbot

Click the chat icon in the bottom right to:
- Ask about the platform
- Get help with features
- Learn about donation process
- Get general assistance

## 🎨 Design Highlights

### Color Scheme
- **Primary**: Purple gradient
- **Secondary**: Blue gradient
- **Accent**: Pink/Yellow gradient

### Animations
- Floating background orbs
- Smooth hover transitions
- Scale-in effects on cards
- Gradient text animations

## 📱 Mobile Responsive

The entire application is fully responsive:
- Mobile phones (320px+)
- Tablets (768px+)
- Desktops (1024px+)
- Large screens (1440px+)

## 🔐 Security Features

- JWT authentication
- Password hashing with bcrypt
- Protected API routes
- Role-based access control
- Secure session management

## 📈 Next Steps

1. Explore all four role dashboards
2. Create sample donations and requests
3. Test the delivery coordination flow
4. Try the AI chatbot
5. Check responsive design on mobile

## 🆘 Need Help?

- Check the main README.md for detailed documentation
- Review API endpoints in README.md
- Check browser console for errors
- Verify both servers are running

## 🎉 Enjoy!

You're all set! Start exploring the beautiful glassmorphism UI and powerful donation management features.
