const User = require('../models/User');
const Donation = require('../models/Donation');
const DonationRequest = require('../models/DonationRequest');
const Delivery = require('../models/Delivery');

const getStats = async (req, res) => {
  try {
    const totalUsers = await User.countDocuments();
    const totalDonations = await Donation.countDocuments();
    const totalRequests = await DonationRequest.countDocuments();
    const totalDeliveries = await Delivery.countDocuments();
    
    const pendingRequests = await DonationRequest.countDocuments({ status: 'pending' });
    const completedDeliveries = await Delivery.countDocuments({ status: 'delivered' });

    res.json({
      totalUsers,
      totalDonations,
      totalRequests,
      totalDeliveries,
      pendingRequests,
      completedDeliveries,
    });
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
};

module.exports = { getStats };