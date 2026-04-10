package com.givehope.backend.security;

import org.springframework.security.core.Authentication;

public final class AuthenticationUtils {

    private AuthenticationUtils() {
    }

    public static String getCurrentUserId(Authentication authentication) {
        if (authentication == null || !(authentication.getPrincipal() instanceof UserPrincipal principal)) {
            throw new IllegalStateException("Authenticated user not available");
        }
        return principal.getId();
    }
}