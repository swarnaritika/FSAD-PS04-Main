const mongoose = require('mongoose');

const donationRequestSchema = mongoose.Schema(
  {
    recipientId: {
      type: mongoose.Schema.Types.ObjectId,
      ref: 'User',
      required: true,
    },
    donationId: {
      type: mongoose.Schema.Types.ObjectId,
      ref: 'Donation',
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
    urgency: {
      type: String,
      enum: ['low', 'normal', 'high', 'emergency'],
      default: 'normal',
    },
    status: {
      type: String,
      enum: ['pending', 'matched', 'delivered', 'cancelled'],
      default: 'pending',
    },
    deliveryAddress: {
      type: String,
      required: [true, 'Please add a delivery address'],
    },
  },
  {
    timestamps: true,
  }
);

module.exports = mongoose.model('DonationRequest', donationRequestSchema);