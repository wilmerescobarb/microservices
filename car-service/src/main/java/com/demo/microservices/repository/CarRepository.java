package com.demo.microservices.repository;

import com.demo.microservices.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Optional<List<Car>> findByUserId(Long userId);
}
