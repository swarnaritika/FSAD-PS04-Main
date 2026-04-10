package com.givehope.backend.controller;

import java.util.Map;

import com.givehope.backend.dto.delivery.DeliveryPayload;
import com.givehope.backend.dto.delivery.DeliveryStatusPayload;
import com.givehope.backend.model.Delivery;
import com.givehope.backend.service.DeliveryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping
    public ResponseEntity<?> getDeliveries() {
        return ResponseEntity.ok(deliveryService.getDeliveries());
    }

    @GetMapping("/coordinator/{coordinatorId}")
    public ResponseEntity<?> getDeliveriesByCoordinator(@PathVariable String coordinatorId) {
        return ResponseEntity.ok(deliveryService.getDeliveriesByCoordinator(coordinatorId));
    }

    @PostMapping
    public ResponseEntity<Delivery> createDelivery(Authentication authentication, @RequestBody DeliveryPayload request) {
        Delivery delivery = deliveryService.createDelivery(authentication, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(delivery);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Delivery> updateDeliveryStatus(@PathVariable String id,
            @RequestBody(required = false) DeliveryStatusPayload request,
            @RequestParam(required = false) String status) {
        String resolvedStatus = status != null ? status : (request == null ? null : request.status());
        return ResponseEntity.ok(deliveryService.updateDeliveryStatus(id, resolvedStatus));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDelivery(@PathVariable String id) {
        deliveryService.deleteDelivery(id);
        return ResponseEntity.ok(Map.of("message", "Delivery removed"));
    }
}