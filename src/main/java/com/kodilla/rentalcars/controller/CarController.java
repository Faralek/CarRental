package com.kodilla.rentalcars.controller;

import com.kodilla.rentalcars.dto.CarDto;
import com.kodilla.rentalcars.exception.CarNotFoundException;
import com.kodilla.rentalcars.mapper.CarMapper;
import com.kodilla.rentalcars.service.CarDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/car")
public class CarController {

    @Autowired
    private CarDbService carDbService;
    @Autowired
    private CarMapper carMapper;

    @RequestMapping(method = RequestMethod.POST, value = "createCar", consumes = APPLICATION_JSON_VALUE)
    public void createCar(@RequestBody CarDto carDto) {
        carDbService.saveCar(carMapper.mapToCar(carDto));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getCars")
    public List<CarDto> getCars() {
        return carMapper.mapToCarDtoList(carDbService.getAllCars());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getCar")
    public CarDto getCar(@RequestParam Long carId) throws CarNotFoundException {
        return carMapper.mapToCarDto(carDbService.getCarById(carId).orElseThrow(()-> new CarNotFoundException("Cart not found "+ carId)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteCar")
    public void deleteCar(@RequestParam Long carId) throws CarNotFoundException {
        try{
            carDbService.deleteById(carId);
        } catch (EmptyResultDataAccessException e){
            throw new CarNotFoundException("Car not found " + carId, e);
        }
    }
}
