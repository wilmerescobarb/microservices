package com.demo.microservices.feignclients;

import com.demo.microservices.model.Bike;
import com.demo.microservices.model.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "bike-service")
@RequestMapping("bike")
public interface BikeFeignClient {
    @PostMapping()
    Bike save(@RequestBody Bike bike);

    @GetMapping("byuser/{userId}")
    public List<Bike> getBikeByUserId(@PathVariable("userId") Long userId);
}
