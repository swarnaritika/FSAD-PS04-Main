package com.givehope.backend.dto.chat;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ChatRequest(
        List<ChatMessage> messages) {
}