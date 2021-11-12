package com.demo.microservices.service;

import com.demo.microservices.entity.User;
import com.demo.microservices.model.Bike;
import com.demo.microservices.model.Car;
import com.demo.microservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RestTemplate restTemplate;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public List<Car> getCars(Long userId){
        List<Car> cars = restTemplate.getForObject("http://localhost:8002/car/byuser/"+userId, List.class);
        return cars;
    }

    public List<Bike> getBikes(Long userId){
        List<Bike> bikes = restTemplate.getForObject("http://localhost:8003/bike/byuser/"+userId, List.class);
        return bikes;
    }

}
