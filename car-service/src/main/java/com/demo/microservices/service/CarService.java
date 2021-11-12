package com.demo.microservices.service;

import com.demo.microservices.entity.Car;
import com.demo.microservices.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    CarRepository carRepository;

    public List<Car> getAllCars(){
        return carRepository.findAll();
    }

    public Car getCarById(Long id){
        return carRepository.findById(id).orElse(null);
    }

    public List<Car> getCarByUserId(Long userId){
        return carRepository.findByUserId(userId).orElse(null);
    }

    public Car saveCar(Car user){
        return carRepository.save(user);
    }
}
