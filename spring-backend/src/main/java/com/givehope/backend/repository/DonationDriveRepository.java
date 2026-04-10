package com.givehope.backend.repository;

import com.givehope.backend.model.DonationDrive;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationDriveRepository extends MongoRepository<DonationDrive, String> {
}