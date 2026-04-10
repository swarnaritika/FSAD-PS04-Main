package com.givehope.backend.service;

import java.util.Locale;

import com.givehope.backend.dto.auth.AuthRequest;
import com.givehope.backend.dto.auth.AuthResponse;
import com.givehope.backend.model.User;
import com.givehope.backend.model.UserRole;
import com.givehope.backend.repository.UserRepository;
import com.givehope.backend.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthResponse register(AuthRequest request) {
        if (request.email() == null || request.email().isBlank() || request.password() == null || request.password().isBlank()
                || request.fullName() == null || request.fullName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user data");
        }

        userRepository.findByEmail(request.email()).ifPresent(existingUser -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists");
        });

        User user = new User();
        user.setFullName(request.fullName());
        user.setEmail(request.email().toLowerCase(Locale.ROOT));
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(normalizeRole(request.role()));

        User savedUser = userRepository.save(user);
        return new AuthResponse(
                jwtService.generateToken(savedUser.getId()),
                savedUser.getId(),
                savedUser.getRole(),
                savedUser.getFullName());
    }

    public AuthResponse login(String email, String password) {
        if (email == null || password == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
        }

        User user = userRepository.findByEmail(email.toLowerCase(Locale.ROOT))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
        }

        return new AuthResponse(
                jwtService.generateToken(user.getId()),
                user.getId(),
                user.getRole(),
                user.getFullName());
    }

    private String normalizeRole(String role) {
        if (role == null || role.isBlank()) {
            return UserRole.DONOR.name();
        }

        try {
            return UserRole.valueOf(role.toUpperCase(Locale.ROOT)).name();
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user data");
        }
    }
}