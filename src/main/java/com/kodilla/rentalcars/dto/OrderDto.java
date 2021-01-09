package com.kodilla.rentalcars.dto;

import com.kodilla.rentalcars.domain.Car;
import com.kodilla.rentalcars.domain.Cart;
import com.kodilla.rentalcars.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private CartDto cart;
    private List<CarDto> cars = new ArrayList<>();
    private UserDto user;
    private BigDecimal sum = new BigDecimal(0);
}
