package com.kodilla.rentalcars.mapper;

import com.kodilla.rentalcars.domain.Order;
import com.kodilla.rentalcars.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private CarMapper carMapper;
    @Autowired
    private UserMapper userMapper;

    public Order mapToOrder(OrderDto orderDto) {
        return new Order(orderDto.getId(),
                cartMapper.mapToCart(orderDto.getCart()),
                carMapper.mapToCarList(orderDto.getCars()),
                userMapper.mapToUser(orderDto.getUser()),
                orderDto.getSum());
    }

    public OrderDto mapToOrderDto(Order order) {
        return new OrderDto(order.getId(),
                cartMapper.mapToCartDto(order.getCart()),
                carMapper.mapToCarDtoList(order.getCars()),
                userMapper.mapToUserDto(order.getUser()),
                order.getSum());
    }

    public List<Order> mapToOrderList(List<OrderDto> orderDtos){
        return orderDtos.stream()
                .map(o -> new Order(o.getId(),
                        cartMapper.mapToCart(o.getCart()),
                        carMapper.mapToCarList(o.getCars()),
                        userMapper.mapToUser(o.getUser()),
                        o.getSum()))
                .collect(Collectors.toList());
    }
    public List<OrderDto> mapToOrderDtoList(List<Order> orders){
        return orders.stream()
                .map(o -> new OrderDto(o.getId(),
                        cartMapper.mapToCartDto(o.getCart()),
                        carMapper.mapToCarDtoList(o.getCars()),
                        userMapper.mapToUserDto(o.getUser()),
                        o.getSum()))
                .collect(Collectors.toList());
    }
}
