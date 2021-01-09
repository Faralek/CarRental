package com.kodilla.rentalcars.mapper;

import com.kodilla.rentalcars.domain.Cart;
import com.kodilla.rentalcars.dto.CarDto;
import com.kodilla.rentalcars.dto.CartDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class CartMapper {

    @Autowired
    private CarMapper carMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserMapper userMapper;

    public Cart mapToCart(CartDto cartDto) {
        return new Cart(cartDto.getId(),
                cartDto.getSum(),
                carMapper.mapToCarList(cartDto.getCars()),
                orderMapper.mapToOrderList(cartDto.getOrders()),
                userMapper.mapToUser(cartDto.getUser()));
    }

    public CartDto mapToCartDto(Cart cart) {
        return new CartDto(cart.getId(),
                cart.getSum(),
                carMapper.mapToCarDtoList(cart.getCars()),
                orderMapper.mapToOrderDtoList(cart.getOrders()),
                userMapper.mapToUserDto(cart.getUser()));
    }

    public List<Cart> mapToCartList(List<CartDto> cartDtos) {
        return cartDtos.stream()
                .map(c -> new Cart(c.getId(),
                        c.getSum(),
                        carMapper.mapToCarList(c.getCars()),
                        orderMapper.mapToOrderList(c.getOrders()),
                        userMapper.mapToUser(c.getUser())))
                .collect(Collectors.toList());
    }

    public List<CartDto> mapToCartDtoList(List<Cart> carts) {
        return carts.stream()
                .map(c -> new CartDto(c.getId(),
                        c.getSum(),
                        carMapper.mapToCarDtoList(c.getCars()),
                        orderMapper.mapToOrderDtoList(c.getOrders()),
                        userMapper.mapToUserDto(c.getUser())))
                .collect(Collectors.toList());
    }
}
