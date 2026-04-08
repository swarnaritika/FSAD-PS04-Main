const express = require('express');
const router = express.Router();
const {
  getDrives,
  createDrive,
  updateDriveStatus,
  deleteDrive,
} = require('../controllers/driveController');
const { protect, admin } = require('../middleware/authMiddleware');

router.get('/', getDrives);
router.post('/', protect, admin, createDrive);
router.put('/:id/status', protect, admin, updateDriveStatus);
router.delete('/:id', protect, admin, deleteDrive);

module.exports = router;