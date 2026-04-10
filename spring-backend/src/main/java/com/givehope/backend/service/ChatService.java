package com.givehope.backend.service;

import java.util.List;
import java.util.Map;

import com.givehope.backend.dto.chat.ChatMessage;
import com.givehope.backend.dto.chat.ChatRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
@SuppressWarnings("null")
public class ChatService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final String openRouterApiKey;
    private final String openRouterUrl;

    public ChatService(
            RestTemplate restTemplate,
            ObjectMapper objectMapper,
            @Value("${app.openrouter.api-key:}") String openRouterApiKey,
            @Value("${app.openrouter.base-url:https://openrouter.ai/api/v1/chat/completions}") String openRouterUrl) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.openRouterApiKey = openRouterApiKey;
        this.openRouterUrl = openRouterUrl;
    }

    public JsonNode getChatResponse(ChatRequest request) {
        if (request.messages() == null || request.messages().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Messages are required and must be an array");
        }

        if (openRouterApiKey == null || openRouterApiKey.isBlank()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to get response from chatbot");
        }

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(openRouterApiKey);
            headers.set("HTTP-Referer", "http://localhost:5173");
            headers.set("X-Title", "GiveHope Chatbot");

            Map<String, Object> payload = Map.of(
                    "model", "openrouter/free",
                    "messages", buildMessages(request.messages()));

            ResponseEntity<String> response = restTemplate.postForEntity(
                    openRouterUrl,
                    new HttpEntity<>(payload, headers),
                    String.class);

            JsonNode root = objectMapper.readTree(response.getBody());
            return root.path("choices").path(0).path("message");
        } catch (RestClientException | java.io.IOException exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to get response from chatbot");
        }
    }

    private List<Map<String, String>> buildMessages(List<ChatMessage> messages) {
        List<Map<String, String>> result = new java.util.ArrayList<>();
        result.add(Map.of(
                "role", "system",
                "content", "You are a helpful assistant for GiveHope, a donation management platform. You help donors, recipients, and logistics coordinators with their questions."));

        for (ChatMessage message : messages) {
            String role = "bot".equalsIgnoreCase(message.role()) ? "assistant" : message.role();
            result.add(Map.of(
                    "role", role,
                    "content", message.content()));
        }

        return result;
    }
}