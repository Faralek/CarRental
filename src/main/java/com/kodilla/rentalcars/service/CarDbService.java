package com.kodilla.rentalcars.service;

import com.kodilla.rentalcars.domain.Car;
import com.kodilla.rentalcars.log.Log;
import com.kodilla.rentalcars.log.LogService;
import com.kodilla.rentalcars.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CarDbService {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private LogService logService;

    public List<Car> getAllCars(){
        return carRepository.findAll();
    }

    public Car saveCar(final Car car){
        logService.saveLog(new Log("Created new car" , LocalDate.now()));
        return carRepository.save(car);
    }

    public void deleteById(final Long id){
        carRepository.deleteById(id);
    }

    public Optional<Car> getCarById(final Long id){
        logService.saveLog(new Log("Deleted car" , LocalDate.now()));
        return carRepository.findById(id);
    }

    public List<Car> findByName(final String name){
        return carRepository.findByName(name);
    }
}
