const express = require('express');
const router = express.Router();
const {
  getRequests,
  getRequestsByRecipient,
  createRequest,
  updateRequest,
  deleteRequest,
} = require('../controllers/requestController');
const { protect } = require('../middleware/authMiddleware');

router.get('/', getRequests);
router.get('/recipient/:recipientId', protect, getRequestsByRecipient);
router.post('/', protect, createRequest);
router.put('/:id', protect, updateRequest);
router.delete('/:id', protect, deleteRequest);

module.exports = router;