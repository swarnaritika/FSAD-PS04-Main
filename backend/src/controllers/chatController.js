const axios = require('axios');

const getChatResponse = async (req, res) => {
  const { messages } = req.body;

  if (!messages || !Array.isArray(messages)) {
    return res.status(400).json({ message: 'Messages are required and must be an array' });
  }

  try {
    const response = await axios.post(
      'https://openrouter.ai/api/v1/chat/completions',
      {
        model: 'openrouter/free', // Using a free model router for stability
        messages: [
          {
            role: 'system',
            content: 'You are a helpful assistant for GiveHope, a donation management platform. You help donors, recipients, and logistics coordinators with their questions.',
          },
          ...messages,
        ],
      },
      {
        headers: {
          Authorization: `Bearer ${process.env.OPENROUTER_API_KEY}`,
          'HTTP-Referer': 'http://localhost:5173', // Optional, for OpenRouter analytics
          'X-Title': 'GiveHope Chatbot', // Optional
          'Content-Type': 'application/json',
        },
      }
    );

    const chatMessage = response.data.choices[0].message;
    res.json(chatMessage);
  } catch (error) {
    console.error('Chatbot API Error:', error.response?.data || error.message);
    res.status(500).json({ 
      message: 'Failed to get response from chatbot',
      error: error.response?.data?.error?.message || error.message 
    });
  }
};

module.exports = { getChatResponse };