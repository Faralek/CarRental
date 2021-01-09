package com.kodilla.rentalcars.dto;

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
public class CartDto {
    private Long id;
    private BigDecimal sum = new BigDecimal(0);
    private List<CarDto> cars = new ArrayList<>();
    private List<OrderDto> orders = new ArrayList<>();
    private UserDto user;
}
