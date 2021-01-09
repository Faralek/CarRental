package com.kodilla.rentalcars.dto;

import com.kodilla.rentalcars.domain.Cart;
import com.kodilla.rentalcars.domain.Extras;
import com.kodilla.rentalcars.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
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
    private List<ExtrasDto> extras;
    private Cart cart;
    private Order order;
}
