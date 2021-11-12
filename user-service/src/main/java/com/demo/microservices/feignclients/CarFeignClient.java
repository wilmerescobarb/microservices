package com.demo.microservices.feignclients;

import com.demo.microservices.model.Bike;
import com.demo.microservices.model.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "car-service")
@RequestMapping("car")
public interface CarFeignClient {
    @PostMapping()
    Car save(@RequestBody Car car);

    @GetMapping("byuser/{userId}")
    List<Car> getCarByUserId(@PathVariable("userId") Long userId);
}
