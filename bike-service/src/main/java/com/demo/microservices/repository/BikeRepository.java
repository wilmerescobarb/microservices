package com.demo.microservices.repository;

import com.demo.microservices.entity.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BikeRepository extends JpaRepository<Bike, Long> {
    Optional<List<Bike>> findByUserId(Long userId);
}
