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
public class CarDto {
    private Long id;
    private String description;
    private String name;
    private BigDecimal dailyPrice;
    private List<ExtrasDto> extrasList = new ArrayList<>();
    private CartDto cart;
    private OrderDto order;
}
