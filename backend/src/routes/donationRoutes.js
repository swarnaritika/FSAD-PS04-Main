const express = require('express');
const router = express.Router();
const {
  getDonations,
  getDonationsByDonor,
  createDonation,
  updateDonation,
  deleteDonation,
} = require('../controllers/donationController');
const { protect } = require('../middleware/authMiddleware');

router.get('/', getDonations);
router.get('/donor/:donorId', protect, getDonationsByDonor);
router.post('/', protect, createDonation);
router.put('/:id', protect, updateDonation);
router.delete('/:id', protect, deleteDonation);

module.exports = router;