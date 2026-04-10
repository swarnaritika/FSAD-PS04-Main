package com.givehope.backend.repository;

import java.util.List;

import com.givehope.backend.model.Donation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationRepository extends MongoRepository<Donation, String> {

    List<Donation> findByDonorId(String donorId);
}