package com.givehope.backend.repository;

import java.util.List;

import com.givehope.backend.model.Delivery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends MongoRepository<Delivery, String> {

    List<Delivery> findByCoordinatorId(String coordinatorId);

    long countByStatus(String status);
}