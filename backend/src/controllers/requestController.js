const DonationRequest = require('../models/DonationRequest');

const getRequests = async (req, res) => {
  try {
    const requests = await DonationRequest.find({}).populate('recipientId', 'fullName email');
    res.json(requests);
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
};

const getRequestsByRecipient = async (req, res) => {
  try {
    const requests = await DonationRequest.find({ recipientId: req.params.recipientId });
    res.json(requests);
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
};

const createRequest = async (req, res) => {
  const { title, description, category, urgency, deliveryAddress, donationId } = req.body;

  try {
    const request = await DonationRequest.create({
      recipientId: req.user._id,
      donationId,
      title,
      description,
      category,
      urgency,
      deliveryAddress,
    });
    res.status(201).json(request);
  } catch (error) {
    res.status(400).json({ message: error.message });
  }
};

const updateRequest = async (req, res) => {
  try {
    const request = await DonationRequest.findById(req.params.id);

    if (request) {
      request.title = req.body.title || request.title;
      request.description = req.body.description || request.description;
      request.category = req.body.category || request.category;
      request.urgency = req.body.urgency || request.urgency;
      request.status = req.body.status || request.status;
      request.deliveryAddress = req.body.deliveryAddress || request.deliveryAddress;
      request.donationId = req.body.donationId || request.donationId;

      const updatedRequest = await request.save();
      res.json(updatedRequest);
    } else {
      res.status(404).json({ message: 'Request not found' });
    }
  } catch (error) {
    res.status(400).json({ message: error.message });
  }
};

const deleteRequest = async (req, res) => {
  try {
    const request = await DonationRequest.findById(req.params.id);

    if (request) {
      await request.deleteOne();
      res.json({ message: 'Request removed' });
    } else {
      res.status(404).json({ message: 'Request not found' });
    }
  } catch (error) {
    res.status(400).json({ message: error.message });
  }
};

module.exports = {
  getRequests,
  getRequestsByRecipient,
  createRequest,
  updateRequest,
  deleteRequest,
};