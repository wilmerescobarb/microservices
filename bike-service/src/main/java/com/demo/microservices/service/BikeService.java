package com.demo.microservices.service;

import com.demo.microservices.entity.Bike;
import com.demo.microservices.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeService {
    @Autowired
    BikeRepository bikeRepository;

    public List<Bike> getAllBikes(){
        return bikeRepository.findAll();
    }

    public Bike getBikeById(Long id){
        return bikeRepository.findById(id).orElse(null);
    }

    public List<Bike> getBikeByUserId(Long userId){
        return bikeRepository.findByUserId(userId).orElse(null);
    }

    public Bike saveBike(Bike bike){
        return bikeRepository.save(bike);
    }
}
