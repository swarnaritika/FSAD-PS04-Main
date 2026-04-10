package com.givehope.backend.service;

import java.time.LocalDate;
import java.util.List;

import com.givehope.backend.dto.drive.DriveCreateRequest;
import com.givehope.backend.model.DonationDrive;
import com.givehope.backend.repository.DonationDriveRepository;
import com.givehope.backend.security.AuthenticationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@SuppressWarnings("null")
public class DonationDriveService {

    private final DonationDriveRepository donationDriveRepository;

    public DonationDriveService(DonationDriveRepository donationDriveRepository) {
        this.donationDriveRepository = donationDriveRepository;
    }

    public List<DonationDrive> getDrives() {
        return donationDriveRepository.findAll();
    }

    public DonationDrive createDrive(Authentication authentication, DriveCreateRequest request) {
        DonationDrive donationDrive = new DonationDrive();
        donationDrive.setTitle(requireValue(request.title(), "Please add a title"));
        donationDrive.setDescription(request.description());
        donationDrive.setGoalAmount(request.goalAmount());
        donationDrive.setCategory(requireValue(request.category(), "Please add a category"));
        donationDrive.setStartDate(requireDate(request.startDate(), "Please add a start date"));
        donationDrive.setEndDate(requireDate(request.endDate(), "Please add an end date"));
        donationDrive.setImageUrl(request.imageUrl());
        donationDrive.setCreatedBy(AuthenticationUtils.getCurrentUserId(authentication));
        donationDrive.setStatus("active");
        return donationDriveRepository.save(donationDrive);
    }

    public DonationDrive updateDriveStatus(String id, String status) {
        DonationDrive donationDrive = donationDriveRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Drive not found"));

        if (status != null && !status.isBlank()) {
            donationDrive.setStatus(status);
        }

        return donationDriveRepository.save(donationDrive);
    }

    public void deleteDrive(String id) {
        DonationDrive donationDrive = donationDriveRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Drive not found"));
        donationDriveRepository.delete(donationDrive);
    }

    private String requireValue(String value, String message) {
        if (value == null || value.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }
        return value;
    }

    private LocalDate requireDate(LocalDate date, String message) {
        if (date == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }
        return date;
    }
}