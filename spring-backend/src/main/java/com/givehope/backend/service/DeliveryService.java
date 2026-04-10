package com.givehope.backend.service;

import java.time.Instant;
import java.util.List;

import com.givehope.backend.dto.delivery.DeliveryPayload;
import com.givehope.backend.model.Delivery;
import com.givehope.backend.repository.DeliveryRepository;
import com.givehope.backend.security.AuthenticationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@SuppressWarnings("null")
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public List<Delivery> getDeliveries() {
        return deliveryRepository.findAll();
    }

    public List<Delivery> getDeliveriesByCoordinator(String coordinatorId) {
        return deliveryRepository.findByCoordinatorId(coordinatorId);
    }

    public Delivery createDelivery(Authentication authentication, DeliveryPayload request) {
        Delivery delivery = new Delivery();
        delivery.setRequestId(requireValue(request.getRequestId(), "Please add a request id"));
        delivery.setCoordinatorId(AuthenticationUtils.getCurrentUserId(authentication));
        delivery.setPickupAddress(requireValue(request.getPickupAddress(), "Please add a pickup address"));
        delivery.setDeliveryAddress(requireValue(request.getDeliveryAddress(), "Please add a delivery address"));
        delivery.setScheduledDate(request.getScheduledDate() == null ? Instant.now() : request.getScheduledDate());
        delivery.setNotes(request.getNotes());
        if (request.getStatus() != null && !request.getStatus().isBlank()) {
            delivery.setStatus(request.getStatus());
        }
        return deliveryRepository.save(delivery);
    }

    public Delivery updateDeliveryStatus(String id, String status) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Delivery not found"));

        if (status != null && !status.isBlank()) {
            delivery.setStatus(status);
        }

        if ("delivered".equalsIgnoreCase(delivery.getStatus())) {
            delivery.setCompletedAt(Instant.now());
        }

        return deliveryRepository.save(delivery);
    }

    public void deleteDelivery(String id) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Delivery not found"));
        deliveryRepository.delete(delivery);
    }

    private String requireValue(String value, String message) {
        if (value == null || value.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }
        return value;
    }
}