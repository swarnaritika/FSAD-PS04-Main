const express = require('express');
const router = express.Router();
const {
  getDeliveries,
  getDeliveriesByCoordinator,
  createDelivery,
  updateDeliveryStatus,
  deleteDelivery,
} = require('../controllers/deliveryController');
const { protect } = require('../middleware/authMiddleware');

router.get('/', getDeliveries);
router.get('/coordinator/:coordinatorId', protect, getDeliveriesByCoordinator);
router.post('/', protect, createDelivery);
router.put('/:id/status', protect, updateDeliveryStatus);
router.delete('/:id', protect, deleteDelivery);

module.exports = router;