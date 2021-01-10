package com.kodilla.rentalcars.controller;

import com.kodilla.rentalcars.domain.Car;
import com.kodilla.rentalcars.dto.CarDto;
import com.kodilla.rentalcars.dto.ExtrasDto;
import com.kodilla.rentalcars.exception.CarNotFoundException;
import com.kodilla.rentalcars.exception.ExtrasNotFoundException;
import com.kodilla.rentalcars.mapper.CarMapper;
import com.kodilla.rentalcars.mapper.ExtrasMapper;
import com.kodilla.rentalcars.service.CarDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1")
public class CarController {

    @Autowired
    private CarDbService carDbService;
    @Autowired
    private CarMapper carMapper;
    @Autowired
    private ExtrasMapper extrasMapper;

    @RequestMapping(method = RequestMethod.POST, value = "/cars", consumes = APPLICATION_JSON_VALUE)
    public void createCar(@RequestBody CarDto carDto) {
        carDbService.saveCar(carMapper.mapToCar(carDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/cars")
    public CarDto updateCar(@RequestBody CarDto carDto) {
        return carMapper.mapToCarDto(carDbService.saveCar(carMapper.mapToCar(carDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/cars/addExtras")
    public void addExtras(@RequestBody ExtrasDto extrasDto, @RequestParam Long id) {
        Car car = carDbService.getCarById(id).get();
        carDbService.getCarById(id).get().addExtras(extrasMapper.mapToExtras(extrasDto));
        carDbService.saveCar(car);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/cars/deleteExtras")
    public void removeExtras(@RequestBody ExtrasDto extrasDto, @RequestParam Long id) throws ExtrasNotFoundException {
        Car car = carDbService.getCarById(id).get();
        carDbService.getCarById(id).get().deleteExtras(extrasMapper.mapToExtras(extrasDto));
        carDbService.saveCar(car);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cars")
    public List<CarDto> getCars() {
        return carMapper.mapToCarDtoList(carDbService.getAllCars());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cars/{carId}")
    public CarDto getCar(@PathVariable Long carId) throws CarNotFoundException {
        return carMapper.mapToCarDto(carDbService.getCarById(carId).orElseThrow(()-> new CarNotFoundException("Cart not found "+ carId)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/cars/{carId}")
    public void deleteCar(@PathVariable Long carId) throws CarNotFoundException {
        try{
            carDbService.deleteById(carId);
        } catch (EmptyResultDataAccessException e){
            throw new CarNotFoundException("Car not found " + carId, e);
        }
    }
}
