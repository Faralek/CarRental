package com.kodilla.rentalcars.service;

import com.kodilla.rentalcars.domain.Car;
import com.kodilla.rentalcars.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarDbService {
    @Autowired
    private CarRepository carRepository;

    public List<Car> getAllCars(){
        return carRepository.findAll();
    }

    public Car saveCar(final Car car){
        return carRepository.save(car);
    }

    public void deleteById(final Long id){
        carRepository.deleteById(id);
    }

    public Optional<Car> getCarById(final Long id){
        return carRepository.findById(id);
    }

    public List<Car> findByName(final String name){
        return carRepository.findByName(name);
    }
}
