package com.givehope.backend.service;

import java.util.List;

import com.givehope.backend.dto.request.DonationRequestPayload;
import com.givehope.backend.model.DonationRequest;
import com.givehope.backend.repository.DonationRequestRepository;
import com.givehope.backend.security.AuthenticationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@SuppressWarnings("null")
public class DonationRequestService {

    private final DonationRequestRepository donationRequestRepository;

    public DonationRequestService(DonationRequestRepository donationRequestRepository) {
        this.donationRequestRepository = donationRequestRepository;
    }

    public List<DonationRequest> getRequests() {
        return donationRequestRepository.findAll();
    }

    public List<DonationRequest> getRequestsByRecipient(String recipientId) {
        return donationRequestRepository.findByRecipientId(recipientId);
    }

    public DonationRequest createRequest(Authentication authentication, DonationRequestPayload request) {
        DonationRequest donationRequest = new DonationRequest();
        donationRequest.setRecipientId(AuthenticationUtils.getCurrentUserId(authentication));
        applyRequestFields(donationRequest, request, true);
        return donationRequestRepository.save(donationRequest);
    }

    public DonationRequest updateRequest(String id, DonationRequestPayload request) {
        DonationRequest donationRequest = donationRequestRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Request not found"));
        applyRequestFields(donationRequest, request, false);
        return donationRequestRepository.save(donationRequest);
    }

    public void deleteRequest(String id) {
        DonationRequest donationRequest = donationRequestRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Request not found"));
        donationRequestRepository.delete(donationRequest);
    }

    private void applyRequestFields(DonationRequest donationRequest, DonationRequestPayload request, boolean isCreate) {
        if (request.getTitle() != null && !request.getTitle().isBlank()) {
            donationRequest.setTitle(request.getTitle());
        } else if (isCreate) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please add a title");
        }

        if (request.getDescription() != null) {
            donationRequest.setDescription(request.getDescription());
        }

        if (request.getCategory() != null && !request.getCategory().isBlank()) {
            donationRequest.setCategory(request.getCategory());
        } else if (isCreate) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please add a category");
        }

        if (request.getUrgency() != null && !request.getUrgency().isBlank()) {
            donationRequest.setUrgency(request.getUrgency());
        } else if (isCreate) {
            donationRequest.setUrgency("normal");
        }

        if (request.getStatus() != null && !request.getStatus().isBlank()) {
            donationRequest.setStatus(request.getStatus());
        }

        if (request.getDeliveryAddress() != null && !request.getDeliveryAddress().isBlank()) {
            donationRequest.setDeliveryAddress(request.getDeliveryAddress());
        } else if (isCreate) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please add a delivery address");
        }

        if (request.getDonationId() != null) {
            donationRequest.setDonationId(request.getDonationId());
        }
    }
}