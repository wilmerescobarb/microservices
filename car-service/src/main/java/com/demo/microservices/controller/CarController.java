package com.demo.microservices.controller;

import com.demo.microservices.entity.Car;
import com.demo.microservices.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {
    @Autowired
    CarService carService;

    @GetMapping()
    public ResponseEntity<List<Car>> getAllCars(){
        List<Car> allCars = carService.getAllCars();
        if(allCars.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(allCars);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable("id") Long id){
        Car carById = carService.getCarById(id);
        if(carById == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(carById);
    }

    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<Car>> getCarByUserId(@PathVariable("userId") Long userId){
        List<Car> carById = carService.getCarByUserId(userId);
        return ResponseEntity.ok(carById);
    }

    @PostMapping()
    public ResponseEntity<Car> saveCar(@RequestBody Car car){
        Car carNew = carService.saveCar(car);
        if(carNew == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(carNew);
    }
}
