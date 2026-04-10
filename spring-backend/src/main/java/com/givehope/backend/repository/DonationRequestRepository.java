package com.givehope.backend.repository;

import java.util.List;

import com.givehope.backend.model.DonationRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationRequestRepository extends MongoRepository<DonationRequest, String> {

    List<DonationRequest> findByRecipientId(String recipientId);

    long countByStatus(String status);
}