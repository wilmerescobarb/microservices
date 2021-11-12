package com.demo.microservices.controller;

import com.demo.microservices.entity.User;
import com.demo.microservices.model.Bike;
import com.demo.microservices.model.Car;
import com.demo.microservices.repository.UserRepository;
import com.demo.microservices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> allUsers = userService.getAllUsers();
        if(allUsers.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(allUsers);

    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id){
        User userById = userService.getUserById(id);
        if(userById == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(userById);
    }

    @PostMapping()
    public ResponseEntity<User> saveUser(@RequestBody User user){
        User userNew = userService.saveUser(user);
        if(userNew == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(userNew);
    }

    @GetMapping("/cars/{userId}")
    public ResponseEntity<List<Car>> getCarsForUserId(@PathVariable("userId") Long userId){
        User user = userService.getUserById(userId);
        if(user==null)
            return ResponseEntity.notFound().build();
        List<Car> carsForUserId = userService.getCars(userId);
        return ResponseEntity.ok(carsForUserId);
    }

    @GetMapping("/bikes/{userId}")
    public ResponseEntity<List<Bike>> getBikesForUserId(@PathVariable("userId") Long userId){
        User user = userService.getUserById(userId);
        if(user==null)
            return ResponseEntity.notFound().build();
        List<Bike> bikesForUserId = userService.getBikes(userId);
        return ResponseEntity.ok(bikesForUserId);
    }

    @PostMapping("/savecar/{userId}")
    public ResponseEntity<Car> saveCarForUserId(@PathVariable("userId") Long userId, @RequestBody Car car){
        Car carNew = userService.saveCar(userId, car);
        if(carNew==null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(carNew);
    }

    @PostMapping("/savebike/{userId}")
    public ResponseEntity<Bike> saveBikeForUserId(@PathVariable("userId") Long userId, @RequestBody Bike bike){
        Bike bikeNew = userService.saveBike(userId, bike);
        if(bikeNew==null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(bikeNew);
    }

    @GetMapping("/getall/{userId}")
    public ResponseEntity<Map<String, Object>> getAllVehiclesForUserId(@PathVariable("userId") Long userId){
        Map<String, Object> response = userService.getUserAndVehicles(userId);
        if(response.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(response);
    }

}
