package com.givehope.backend.controller;

import java.util.Map;

import com.givehope.backend.dto.drive.DriveCreateRequest;
import com.givehope.backend.dto.drive.DriveStatusPayload;
import com.givehope.backend.model.DonationDrive;
import com.givehope.backend.service.DonationDriveService;
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
@RequestMapping("/api/v1/drives")
public class DriveController {

    private final DonationDriveService donationDriveService;

    public DriveController(DonationDriveService donationDriveService) {
        this.donationDriveService = donationDriveService;
    }

    @GetMapping
    public ResponseEntity<?> getDrives() {
        return ResponseEntity.ok(donationDriveService.getDrives());
    }

    @PostMapping
    public ResponseEntity<DonationDrive> createDrive(Authentication authentication, @RequestBody DriveCreateRequest request) {
        DonationDrive donationDrive = donationDriveService.createDrive(authentication, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(donationDrive);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<DonationDrive> updateDriveStatus(@PathVariable String id,
            @RequestBody(required = false) DriveStatusPayload request,
            @RequestParam(required = false) String status) {
        String resolvedStatus = status != null ? status : (request == null ? null : request.status());
        return ResponseEntity.ok(donationDriveService.updateDriveStatus(id, resolvedStatus));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDrive(@PathVariable String id) {
        donationDriveService.deleteDrive(id);
        return ResponseEntity.ok(Map.of("message", "Drive removed"));
    }
}