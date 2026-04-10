package com.givehope.backend.controller;

import java.util.Map;

import com.givehope.backend.dto.donation.DonationUpsertRequest;
import com.givehope.backend.model.Donation;
import com.givehope.backend.service.DonationService;
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
@RequestMapping("/api/v1/donations")
public class DonationController {

    private final DonationService donationService;

    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }

    @GetMapping
    public ResponseEntity<?> getDonations() {
        return ResponseEntity.ok(donationService.getDonations());
    }

    @GetMapping("/donor/{donorId}")
    public ResponseEntity<?> getDonationsByDonor(@PathVariable String donorId) {
        return ResponseEntity.ok(donationService.getDonationsByDonor(donorId));
    }

    @PostMapping
    public ResponseEntity<Donation> createDonation(Authentication authentication, @RequestBody DonationUpsertRequest request) {
        Donation donation = donationService.createDonation(authentication, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(donation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Donation> updateDonation(@PathVariable String id, @RequestBody DonationUpsertRequest request) {
        return ResponseEntity.ok(donationService.updateDonation(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDonation(@PathVariable String id) {
        donationService.deleteDonation(id);
        return ResponseEntity.ok(Map.of("message", "Donation removed"));
    }
}