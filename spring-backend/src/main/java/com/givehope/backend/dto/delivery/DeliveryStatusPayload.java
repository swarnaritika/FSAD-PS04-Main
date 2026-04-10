package com.givehope.backend.dto.delivery;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DeliveryStatusPayload(
        String status) {
}