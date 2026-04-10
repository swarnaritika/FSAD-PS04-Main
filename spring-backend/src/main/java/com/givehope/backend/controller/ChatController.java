package com.givehope.backend.controller;

import com.givehope.backend.dto.chat.ChatRequest;
import com.givehope.backend.service.ChatService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public ResponseEntity<JsonNode> getChatResponse(@RequestBody ChatRequest request) {
        return ResponseEntity.ok(chatService.getChatResponse(request));
    }
}