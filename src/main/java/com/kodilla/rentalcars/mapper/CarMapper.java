package com.kodilla.rentalcars.mapper;

import com.kodilla.rentalcars.domain.Car;
import com.kodilla.rentalcars.dto.CarDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class CarMapper {
    @Autowired
    private ExtrasMapper extrasMapper;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private OrderMapper orderMapper;

    public CarDto mapToCarDto(Car car) {
        return new CarDto(car.getId(),
                car.getDescription(),
                car.getName(),
                car.getDailyPrice(),
                extrasMapper.mapToExtrasDtoList(car.getExtras()),
                cartMapper.mapToCartDto(car.getCart()),
                orderMapper.mapToOrderDto(car.getOrder()));
    }

    public Car mapToCar(CarDto carDto) {
        return new Car(carDto.getId(),
                carDto.getDescription(),
                carDto.getName(),
                carDto.getDailyPrice(),
                extrasMapper.mapToExtrasList(carDto.getExtras()),
                cartMapper.mapToCart(carDto.getCart()),
                orderMapper.mapToOrder(carDto.getOrder()));
    }

    public List<Car> mapToCarList(List<CarDto> carDtos) {
        return carDtos.stream()
                .map(c -> new Car(
                        c.getId(),
                        c.getDescription(),
                        c.getName(),
                        c.getDailyPrice(),
                        extrasMapper.mapToExtrasList(c.getExtras()),
                        cartMapper.mapToCart(c.getCart()),
                        orderMapper.mapToOrder(c.getOrder())))
                .collect(Collectors.toList());
    }

    public List<CarDto> mapToCarDtoList(List<Car> cars) {
        return cars.stream()
                .map(c -> new CarDto(
                        c.getId(),
                        c.getDescription(),
                        c.getName(),
                        c.getDailyPrice(),
                        extrasMapper.mapToExtrasDtoList(c.getExtras()),
                        cartMapper.mapToCartDto(c.getCart()),
                        orderMapper.mapToOrderDto(c.getOrder())))
                .collect(Collectors.toList());
    }
}



