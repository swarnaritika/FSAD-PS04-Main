const Donation = require('../models/Donation');

const getDonations = async (req, res) => {
  try {
    const donations = await Donation.find({}).populate('donorId', 'fullName email');
    res.json(donations);
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
};

const getDonationsByDonor = async (req, res) => {
  try {
    const donations = await Donation.find({ donorId: req.params.donorId });
    res.json(donations);
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
};

const createDonation = async (req, res) => {
  const { title, description, category, quantity, condition, pickupAddress, imageUrl } = req.body;

  try {
    const donation = await Donation.create({
      donorId: req.user._id,
      title,
      description,
      category,
      quantity,
      condition,
      pickupAddress,
      imageUrl,
    });
    res.status(201).json(donation);
  } catch (error) {
    res.status(400).json({ message: error.message });
  }
};

const updateDonation = async (req, res) => {
  try {
    const donation = await Donation.findById(req.params.id);

    if (donation) {
      donation.title = req.body.title || donation.title;
      donation.description = req.body.description || donation.description;
      donation.category = req.body.category || donation.category;
      donation.quantity = req.body.quantity || donation.quantity;
      donation.condition = req.body.condition || donation.condition;
      donation.pickupAddress = req.body.pickupAddress || donation.pickupAddress;
      donation.status = req.body.status || donation.status;
      donation.imageUrl = req.body.imageUrl || donation.imageUrl;

      const updatedDonation = await donation.save();
      res.json(updatedDonation);
    } else {
      res.status(404).json({ message: 'Donation not found' });
    }
  } catch (error) {
    res.status(400).json({ message: error.message });
  }
};

const deleteDonation = async (req, res) => {
  try {
    const donation = await Donation.findById(req.params.id);

    if (donation) {
      await donation.deleteOne();
      res.json({ message: 'Donation removed' });
    } else {
      res.status(404).json({ message: 'Donation not found' });
    }
  } catch (error) {
    res.status(400).json({ message: error.message });
  }
};

module.exports = {
  getDonations,
  getDonationsByDonor,
  createDonation,
  updateDonation,
  deleteDonation,
};