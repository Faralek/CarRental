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
public class ExtrasDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private List<CarDto> cars = new ArrayList<>();
}
