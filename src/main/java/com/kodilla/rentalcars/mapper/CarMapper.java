package com.kodilla.rentalcars.mapper;

import com.kodilla.rentalcars.domain.Car;
import com.kodilla.rentalcars.dto.CarDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public CarDto mapToCarDto(Car car) {
        CarDto carDto = modelMapper.map(car, CarDto.class);
        return carDto;
    }


    public Car mapToCar(CarDto carDto) {
        Car car = modelMapper.map(carDto, Car.class);
        return car;
    }

    public List<Car> mapToCarList(final List<CarDto> carDtos) {
        if (carDtos.isEmpty()) {
            return new ArrayList<>();
        } else {
            return carDtos.stream()
                    .map(this::mapToCar)
                    .collect(Collectors.toList());
        }
    }

    public List<CarDto> mapToCarDtoList(final List<Car> cars) {
        if (cars.isEmpty()) {
            return new ArrayList<>();
        } else {
            return cars.stream()
                    .map(this::mapToCarDto)
                    .collect(Collectors.toList());
        }
    }
}



