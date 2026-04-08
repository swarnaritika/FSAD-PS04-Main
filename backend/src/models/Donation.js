const mongoose = require('mongoose');

const donationSchema = mongoose.Schema(
  {
    donorId: {
      type: mongoose.Schema.Types.ObjectId,
      ref: 'User',
      required: true,
    },
    title: {
      type: String,
      required: [true, 'Please add a title'],
    },
    description: String,
    category: {
      type: String,
      required: [true, 'Please add a category'],
    },
    quantity: {
      type: Number,
      required: [true, 'Please add quantity'],
      default: 1,
    },
    condition: {
      type: String,
      enum: ['new', 'like_new', 'good', 'fair'],
      default: 'good',
    },
    status: {
      type: String,
      enum: ['available', 'claimed', 'delivered'],
      default: 'available',
    },
    pickupAddress: String,
    imageUrl: String,
  },
  {
    timestamps: true,
  }
);

module.exports = mongoose.model('Donation', donationSchema);