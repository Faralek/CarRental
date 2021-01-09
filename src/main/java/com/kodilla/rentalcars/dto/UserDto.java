package com.kodilla.rentalcars.dto;

import com.kodilla.rentalcars.domain.Cart;
import com.kodilla.rentalcars.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private Cart cart;
    private List<OrderDto> orders = new ArrayList<>();
}
