package com.givehope.backend.dto.drive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DriveStatusPayload(
        String status) {
}