package com.givehope.backend.dto.drive;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DriveCreateRequest(
        @NotBlank String title,
        String description,
        @NotNull Double goalAmount,
        @NotBlank String category,
        @NotNull LocalDate startDate,
        @NotNull LocalDate endDate,
        String imageUrl) {
}