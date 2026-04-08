const Delivery = require('../models/Delivery');

const getDeliveries = async (req, res) => {
  try {
    const deliveries = await Delivery.find({}).populate('requestId').populate('coordinatorId', 'fullName email');
    res.json(deliveries);
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
};

const getDeliveriesByCoordinator = async (req, res) => {
  try {
    const deliveries = await Delivery.find({ coordinatorId: req.params.coordinatorId }).populate('requestId');
    res.json(deliveries);
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
};

const createDelivery = async (req, res) => {
  const { requestId, pickupAddress, deliveryAddress, scheduledDate, notes } = req.body;

  try {
    const delivery = await Delivery.create({
      requestId,
      coordinatorId: req.user._id,
      pickupAddress,
      deliveryAddress,
      scheduledDate: scheduledDate || new Date(),
      notes,
    });
    res.status(201).json(delivery);
  } catch (error) {
    res.status(400).json({ message: error.message });
  }
};

const updateDeliveryStatus = async (req, res) => {
  try {
    const delivery = await Delivery.findById(req.params.id);

    if (delivery) {
      delivery.status = req.body.status || delivery.status;
      if (delivery.status === 'delivered') {
        delivery.completedAt = new Date();
      }

      const updatedDelivery = await delivery.save();
      res.json(updatedDelivery);
    } else {
      res.status(404).json({ message: 'Delivery not found' });
    }
  } catch (error) {
    res.status(400).json({ message: error.message });
  }
};

const deleteDelivery = async (req, res) => {
  try {
    const delivery = await Delivery.findById(req.params.id);

    if (delivery) {
      await delivery.deleteOne();
      res.json({ message: 'Delivery removed' });
    } else {
      res.status(404).json({ message: 'Delivery not found' });
    }
  } catch (error) {
    res.status(400).json({ message: error.message });
  }
};

module.exports = {
  getDeliveries,
  getDeliveriesByCoordinator,
  createDelivery,
  updateDeliveryStatus,
  deleteDelivery,
};