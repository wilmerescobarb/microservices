package com.demo.microservices.controller;

import com.demo.microservices.entity.User;
import com.demo.microservices.repository.UserRepository;
import com.demo.microservices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
