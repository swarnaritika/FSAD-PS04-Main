const express = require('express');
const dotenv = require('dotenv');
const cors = require('cors');
const connectDB = require('./config/db');

dotenv.config();
connectDB();

const app = express();

app.use(cors());
app.use(express.json());

// Routes
app.use('/api/v1/auth', require('./routes/userRoutes'));
app.use('/api/v1/donations', require('./routes/donationRoutes'));
app.use('/api/v1/requests', require('./routes/requestRoutes'));
app.use('/api/v1/deliveries', require('./routes/deliveryRoutes'));
app.use('/api/v1/drives', require('./routes/driveRoutes'));
app.use('/api/v1/stats', require('./routes/statsRoutes'));
app.use('/api/v1/chat', require('./routes/chatRoutes'));

app.get('/', (req, res) => {
  res.send('API is running...');
});

const PORT = process.env.PORT || 8084;

app.listen(PORT, () => {
  console.log(`Server running in ${process.env.NODE_ENV || 'development'} mode on port ${PORT}`);
});