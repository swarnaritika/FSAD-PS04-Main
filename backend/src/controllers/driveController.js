const DonationDrive = require('../models/DonationDrive');

const getDrives = async (req, res) => {
  try {
    const drives = await DonationDrive.find({});
    res.json(drives);
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
};

const createDrive = async (req, res) => {
  const { title, description, goalAmount, category, startDate, endDate, imageUrl } = req.body;

  try {
    const drive = await DonationDrive.create({
      title,
      description,
      goalAmount,
      category,
      startDate,
      endDate,
      imageUrl,
      createdBy: req.user._id,
    });
    res.status(201).json(drive);
  } catch (error) {
    res.status(400).json({ message: error.message });
  }
};

const updateDriveStatus = async (req, res) => {
  try {
    const drive = await DonationDrive.findById(req.params.id);

    if (drive) {
      drive.status = req.query.status || drive.status;
      const updatedDrive = await drive.save();
      res.json(updatedDrive);
    } else {
      res.status(404).json({ message: 'Drive not found' });
    }
  } catch (error) {
    res.status(400).json({ message: error.message });
  }
};

const deleteDrive = async (req, res) => {
  try {
    const drive = await DonationDrive.findById(req.params.id);

    if (drive) {
      await drive.deleteOne();
      res.json({ message: 'Drive removed' });
    } else {
      res.status(404).json({ message: 'Drive not found' });
    }
  } catch (error) {
    res.status(400).json({ message: error.message });
  }
};

module.exports = {
  getDrives,
  createDrive,
  updateDriveStatus,
  deleteDrive,
};