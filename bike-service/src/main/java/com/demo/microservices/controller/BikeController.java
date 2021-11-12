package com.demo.microservices.controller;

import com.demo.microservices.entity.Bike;
import com.demo.microservices.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bike")
public class BikeController {
    @Autowired
    BikeService bikeService;

    @GetMapping()
    public ResponseEntity<List<Bike>> getAllBikes(){
        List<Bike> allBikes = bikeService.getAllBikes();
        if(allBikes.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(allBikes);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Bike> getBikeById(@PathVariable("id") Long id){
        Bike bikeById = bikeService.getBikeById(id);
        if(bikeById == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(bikeById);
    }

    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<Bike>> getBikeByUserId(@PathVariable("userId") Long userId){
        List<Bike> bikeById = bikeService.getBikeByUserId(userId);
        if(bikeById.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(bikeById);
    }

    @PostMapping()
    public ResponseEntity<Bike> saveBike(@RequestBody Bike bike){
        Bike bikeNew = bikeService.saveBike(bike);
        if(bikeNew == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(bikeNew);
    }
}
