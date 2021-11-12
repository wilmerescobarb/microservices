package com.demo.microservices.service;

import com.demo.microservices.entity.User;
import com.demo.microservices.feignclients.BikeFeignClient;
import com.demo.microservices.feignclients.CarFeignClient;
import com.demo.microservices.model.Bike;
import com.demo.microservices.model.Car;
import com.demo.microservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CarFeignClient carFeignClient;

    @Autowired
    BikeFeignClient bikeFeignClient;

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
        List<Car> cars = restTemplate.getForObject("http://car-service/car/byuser/"+userId, List.class);
        return cars;
    }

    public List<Bike> getBikes(Long userId){
        List<Bike> bikes = restTemplate.getForObject("http://bike-service/bike/byuser/"+userId, List.class);
        return bikes;
    }

    public Car saveCar(Long userId, Car car){
        car.setUserId(userId);
        Car carNew = carFeignClient.save(car);
        return carNew;
    }

    public Bike saveBike(Long userId, Bike bike){
        bike.setUserId(userId);
        Bike bikeNew = bikeFeignClient.save(bike);
        return bikeNew;
    }

    public Map<String, Object> getUserAndVehicles(Long userId){
        Map<String, Object> response = new HashMap<>();
        User user = userRepository.findById(userId).orElse(null);
        if(user == null){
            response.put("mensaje", "Usuario no existe");
        }else{
            response.put("User", user);

            List<Car> cars = carFeignClient.getCarByUserId(userId);
            List<Bike> bikes = bikeFeignClient.getBikeByUserId(userId);

            response.put("cars", cars.isEmpty()?"El usuario no tiene Cars": cars);
            response.put("bikes", bikes.isEmpty()?"El usuario no tiene Bikes": bikes);
        }

        return response;
    }


}
