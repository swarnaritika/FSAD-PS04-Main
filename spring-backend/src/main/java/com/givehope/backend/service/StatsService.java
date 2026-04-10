package com.givehope.backend.service;

import java.util.LinkedHashMap;
import java.util.Map;

import com.givehope.backend.repository.DeliveryRepository;
import com.givehope.backend.repository.DonationRepository;
import com.givehope.backend.repository.DonationRequestRepository;
import com.givehope.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class StatsService {

    private final UserRepository userRepository;
    private final DonationRepository donationRepository;
    private final DonationRequestRepository donationRequestRepository;
    private final DeliveryRepository deliveryRepository;

    public StatsService(
            UserRepository userRepository,
            DonationRepository donationRepository,
            DonationRequestRepository donationRequestRepository,
            DeliveryRepository deliveryRepository) {
        this.userRepository = userRepository;
        this.donationRepository = donationRepository;
        this.donationRequestRepository = donationRequestRepository;
        this.deliveryRepository = deliveryRepository;
    }

    public Map<String, Long> getStats() {
        Map<String, Long> stats = new LinkedHashMap<>();
        stats.put("totalUsers", userRepository.count());
        stats.put("totalDonations", donationRepository.count());
        stats.put("totalRequests", donationRequestRepository.count());
        stats.put("totalDeliveries", deliveryRepository.count());
        stats.put("pendingRequests", donationRequestRepository.countByStatus("pending"));
        stats.put("completedDeliveries", deliveryRepository.countByStatus("delivered"));
        return stats;
    }
}