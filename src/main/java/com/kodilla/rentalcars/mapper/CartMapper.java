package com.kodilla.rentalcars.mapper;

import com.kodilla.rentalcars.domain.Cart;
import com.kodilla.rentalcars.dto.CarDto;
import com.kodilla.rentalcars.dto.CartDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public Cart mapToCart(CartDto cartDto) {
        Cart cart = modelMapper.map(cartDto, Cart.class);
        return cart;
    }

    public CartDto mapToCartDto(Cart cart)  {
        CartDto cartDto = modelMapper.map(cart, CartDto.class);
        return cartDto;
    }

    public List<CartDto> mapToCartDtoList(final List<Cart> carts) {
        if (carts.isEmpty()) {
            return new ArrayList<>();
        } else {
            return carts.stream()
                    .map(this::mapToCartDto)
                    .collect(Collectors.toList());
        }
    }
}
