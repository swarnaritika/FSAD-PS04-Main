const mongoose = require('mongoose');
const dotenv = require('dotenv');
const User = require('./src/models/User');
const DonationDrive = require('./src/models/DonationDrive');
const Donation = require('./src/models/Donation');
const DonationRequest = require('./src/models/DonationRequest');

dotenv.config();

const seedData = async () => {
  try {
    await mongoose.connect(process.env.MONGODB_URI);
    console.log('MongoDB Connected for seeding...');

    // Clear existing data
    await User.deleteMany();
    await DonationDrive.deleteMany();
    await Donation.deleteMany();
    await DonationRequest.deleteMany();

    // Create Users
    const admin = await User.create({
      fullName: 'Admin User',
      email: 'admin@example.com',
      password: 'password123',
      role: 'ADMIN',
    });

    const donor = await User.create({
      fullName: 'John Donor',
      email: 'donor@example.com',
      password: 'password123',
      role: 'DONOR',
    });

    const recipient = await User.create({
      fullName: 'Jane Recipient',
      email: 'recipient@example.com',
      password: 'password123',
      role: 'RECIPIENT',
    });

    // Create Donation Drives
    await DonationDrive.create([
      {
        title: 'Winter Clothing Drive',
        description: 'Collecting warm clothes for those in need during winter.',
        category: 'Clothing',
        location: 'Downtown Community Center',
        startDate: new Date(),
        endDate: new Date(Date.now() + 30 * 24 * 60 * 60 * 1000),
        goalAmount: 500,
        currentAmount: 120,
        status: 'active',
        imageUrl: 'https://images.unsplash.com/photo-1488521787991-ed7bbaae773c?w=400&h=250&fit=crop',
        createdBy: admin._id,
      },
      {
        title: 'Emergency Food Relief',
        description: 'Providing non-perishable food items to local food banks.',
        category: 'Food',
        location: 'City Food Bank',
        startDate: new Date(),
        endDate: new Date(Date.now() + 15 * 24 * 60 * 60 * 1000),
        goalAmount: 1000,
        currentAmount: 450,
        status: 'active',
        imageUrl: 'https://images.unsplash.com/photo-1488521787991-ed7bbaae773c?w=400&h=250&fit=crop',
        createdBy: admin._id,
      }
    ]);

    // Create some donations
    await Donation.create({
      donorId: donor._id,
      title: 'Warm Jackets',
      description: 'Gently used winter jackets for adults.',
      category: 'Clothing',
      quantity: 5,
      condition: 'good',
      pickupAddress: '123 Donor St, City',
      status: 'available'
    });

    console.log('Data seeded successfully!');
    process.exit();
  } catch (error) {
    console.error('Error seeding data:', error);
    process.exit(1);
  }
};

seedData();