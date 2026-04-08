# GiveHope - Donation Management System

A modern, full-stack donation management platform with a beautiful glassmorphism UI design. Connect donors with recipients through transparent, efficient coordination.

## 🎨 Design Style

This project features a **Glassmorphism UI** design with:
- Frosted glass effects with backdrop blur
- Gradient color schemes (purple, blue, pink)
- Smooth animations and transitions
- Modern, clean, and professional appearance
- Fully responsive across all devices

## ✨ Features

### For Donors
- List items for donation
- Track donation status
- View impact statistics
- Manage donation history

### For Recipients
- Submit support requests
- Browse available donations
- Claim needed items
- Track request status

### For Logistics Coordinators
- View pending requests
- Assign deliveries
- Track delivery status
- Manage pickup and delivery addresses

### For Administrators
- Create donation drives
- Monitor platform statistics
- Manage campaigns
- View overall impact

### Additional Features
- AI-powered chatbot assistant
- Real-time notifications
- Secure authentication
- Role-based access control

## 🚀 Tech Stack

### Frontend
- React 18
- Vite
- TailwindCSS (with custom glassmorphism utilities)
- Radix UI Components
- React Router
- React Query
- Axios

### Backend
- Node.js
- Express.js
- MongoDB with Mongoose
- JWT Authentication
- bcrypt for password hashing

## 📋 Prerequisites

- Node.js (v16 or higher)
- MongoDB (local or Atlas)
- npm or yarn

## 🛠️ Installation & Setup

### 1. Clone the Repository
```bash
git clone <repository-url>
cd FSAD-PS04-main
```

### 2. Backend Setup
```bash
cd backend
npm install
```

Create a `.env` file in the backend directory (already exists with configuration):
```env
PORT=8084
MONGODB_URI=your_mongodb_connection_string
JWT_SECRET=your_jwt_secret
JWT_EXPIRE=24h
OPENROUTER_API_KEY=your_openrouter_api_key
```

### 3. Frontend Setup
```bash
cd frontend
npm install
```

### 4. Run the Application

#### Option 1: Run Both Servers Separately

Terminal 1 - Backend:
```bash
cd backend
npm run dev
```

Terminal 2 - Frontend:
```bash
cd frontend
npm run dev
```

#### Option 2: Run Concurrently (from frontend directory)
```bash
cd frontend
npm start
```

The application will be available at:
- Frontend: http://localhost:5173
- Backend API: http://localhost:8084

## 👥 User Roles & Test Accounts

You can register new accounts with any of these roles:
- **Admin** - Full platform management
- **Donor** - List and manage donations
- **Recipient** - Request and claim items
- **Logistics** - Coordinate deliveries

## 📁 Project Structure

```
FSAD-PS04-main/
├── backend/
│   ├── src/
│   │   ├── config/         # Database configuration
│   │   ├── controllers/    # Route controllers
│   │   ├── middleware/     # Auth middleware
│   │   ├── models/         # Mongoose models
│   │   ├── routes/         # API routes
│   │   └── index.js        # Server entry point
│   ├── .env                # Environment variables
│   └── package.json
│
├── frontend/
│   ├── src/
│   │   ├── assets/         # Images and styles
│   │   │   └── css/
│   │   │       └── index.css  # Glassmorphism styles
│   │   ├── components/     # React components
│   │   │   ├── dashboards/ # Role-specific dashboards
│   │   │   └── ui/         # Reusable UI components
│   │   ├── contexts/       # React contexts
│   │   ├── lib/            # API configuration
│   │   ├── pages/          # Page components
│   │   ├── App.jsx         # Main app component
│   │   └── main.jsx        # Entry point
│   ├── index.html
│   ├── tailwind.config.js  # Tailwind configuration
│   └── package.json
│
└── README.md
```

## 🎨 Glassmorphism Design System

### Color Palette
- **Primary**: Purple gradient (#667eea → #764ba2)
- **Secondary**: Blue gradient (#4facfe → #00f2fe)
- **Accent**: Pink/Yellow gradient (#fa709a → #fee140)

### Custom CSS Classes
- `.glass` - Basic glass effect
- `.glass-card` - Glass card with rounded corners
- `.glass-strong` - Stronger glass effect
- `.text-gradient` - Gradient text effect
- `.bg-gradient-hero` - Hero gradient background
- `.shadow-glass` - Glass shadow effect
- `.animate-float` - Floating animation

## 🔧 API Endpoints

### Authentication
- `POST /api/v1/auth/register` - Register new user
- `POST /api/v1/auth/login` - Login user

### Donations
- `GET /api/v1/donations` - Get all donations
- `POST /api/v1/donations` - Create donation
- `PUT /api/v1/donations/:id` - Update donation
- `DELETE /api/v1/donations/:id` - Delete donation

### Requests
- `GET /api/v1/requests` - Get all requests
- `POST /api/v1/requests` - Create request
- `PUT /api/v1/requests/:id` - Update request

### Deliveries
- `GET /api/v1/deliveries` - Get all deliveries
- `POST /api/v1/deliveries` - Create delivery
- `PUT /api/v1/deliveries/:id` - Update delivery

### Drives
- `GET /api/v1/drives` - Get all drives
- `POST /api/v1/drives` - Create drive
- `PUT /api/v1/drives/:id` - Update drive
- `DELETE /api/v1/drives/:id` - Delete drive

### Stats
- `GET /api/v1/stats/overview` - Get platform statistics

### Chat
- `POST /api/v1/chat` - Send message to AI assistant

## 🐛 Troubleshooting

### MongoDB Connection Issues
- Ensure MongoDB is running locally or check your Atlas connection string
- Verify network access in MongoDB Atlas

### Port Already in Use
- Change the PORT in backend/.env
- Update the API baseURL in frontend/src/lib/api.js

### CORS Errors
- Backend already configured with CORS
- Ensure frontend is making requests to correct backend URL

## 📝 Development Notes

- The project uses ES6+ features
- Backend uses CommonJS modules
- Frontend uses ES modules
- All passwords are hashed with bcrypt
- JWT tokens expire after 24 hours
- MongoDB ObjectIds are used for all references

## 🚀 Deployment

### Backend Deployment (Heroku/Railway/Render)
1. Set environment variables
2. Ensure MongoDB Atlas is accessible
3. Deploy backend code

### Frontend Deployment (Vercel/Netlify)
1. Update API baseURL to production backend URL
2. Build the project: `npm run build`
3. Deploy the `dist` folder

## 📄 License

This project is for educational purposes.

## 👨‍💻 Author

Developed as part of FSAD-PS04 project

## 🙏 Acknowledgments

- Radix UI for component primitives
- TailwindCSS for styling utilities
- Lucide React for icons
- OpenRouter for AI chat capabilities
