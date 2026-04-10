package com.givehope.backend.dto.auth;

public record AuthResponse(
        String token,
        String userId,
        String role,
        String fullName) {
}