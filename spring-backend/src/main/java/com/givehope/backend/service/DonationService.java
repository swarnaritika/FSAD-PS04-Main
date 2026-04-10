package com.givehope.backend.service;

import java.util.List;

import com.givehope.backend.dto.donation.DonationUpsertRequest;
import com.givehope.backend.model.Donation;
import com.givehope.backend.repository.DonationRepository;
import com.givehope.backend.security.AuthenticationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@SuppressWarnings("null")
public class DonationService {

    private final DonationRepository donationRepository;

    public DonationService(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    public List<Donation> getDonations() {
        return donationRepository.findAll();
    }

    public List<Donation> getDonationsByDonor(String donorId) {
        return donationRepository.findByDonorId(donorId);
    }

    public Donation createDonation(Authentication authentication, DonationUpsertRequest request) {
        Donation donation = new Donation();
        donation.setDonorId(AuthenticationUtils.getCurrentUserId(authentication));
        applyDonationFields(donation, request, true);
        return donationRepository.save(donation);
    }

    public Donation updateDonation(String id, DonationUpsertRequest request) {
        Donation donation = donationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Donation not found"));
        applyDonationFields(donation, request, false);
        return donationRepository.save(donation);
    }

    public void deleteDonation(String id) {
        Donation donation = donationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Donation not found"));
        donationRepository.delete(donation);
    }

    private void applyDonationFields(Donation donation, DonationUpsertRequest request, boolean isCreate) {
        if (request.getTitle() != null && !request.getTitle().isBlank()) {
            donation.setTitle(request.getTitle());
        } else if (isCreate) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please add a title");
        }

        if (request.getDescription() != null) {
            donation.setDescription(request.getDescription());
        }

        if (request.getCategory() != null && !request.getCategory().isBlank()) {
            donation.setCategory(request.getCategory());
        } else if (isCreate) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please add a category");
        }

        if (request.getQuantity() != null) {
            donation.setQuantity(request.getQuantity());
        } else if (isCreate) {
            donation.setQuantity(1);
        }

        if (request.getCondition() != null && !request.getCondition().isBlank()) {
            donation.setCondition(request.getCondition());
        } else if (isCreate) {
            donation.setCondition("good");
        }

        if (request.getStatus() != null && !request.getStatus().isBlank()) {
            donation.setStatus(request.getStatus());
        }

        if (request.getPickupAddress() != null) {
            donation.setPickupAddress(request.getPickupAddress());
        }

        if (request.getImageUrl() != null) {
            donation.setImageUrl(request.getImageUrl());
        }
    }
}