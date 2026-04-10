package com.givehope.backend.controller;

import java.util.Map;

import com.givehope.backend.dto.request.DonationRequestPayload;
import com.givehope.backend.model.DonationRequest;
import com.givehope.backend.service.DonationRequestService;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/requests")
public class DonationRequestController {

    private final DonationRequestService donationRequestService;

    public DonationRequestController(DonationRequestService donationRequestService) {
        this.donationRequestService = donationRequestService;
    }

    @GetMapping
    public ResponseEntity<?> getRequests() {
        return ResponseEntity.ok(donationRequestService.getRequests());
    }

    @GetMapping("/recipient/{recipientId}")
    public ResponseEntity<?> getRequestsByRecipient(@PathVariable String recipientId) {
        return ResponseEntity.ok(donationRequestService.getRequestsByRecipient(recipientId));
    }

    @PostMapping
    public ResponseEntity<DonationRequest> createRequest(Authentication authentication,
            @RequestBody DonationRequestPayload request) {
        DonationRequest donationRequest = donationRequestService.createRequest(authentication, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(donationRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DonationRequest> updateRequest(@PathVariable String id,
            @RequestBody DonationRequestPayload request) {
        return ResponseEntity.ok(donationRequestService.updateRequest(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRequest(@PathVariable String id) {
        donationRequestService.deleteRequest(id);
        return ResponseEntity.ok(Map.of("message", "Request removed"));
    }
}