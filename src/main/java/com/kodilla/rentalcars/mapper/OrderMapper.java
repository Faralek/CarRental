package com.kodilla.rentalcars.mapper;

import com.kodilla.rentalcars.domain.Order;
import com.kodilla.rentalcars.dto.OrderDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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

    private final ModelMapper modelMapper = new ModelMapper();

    public Order mapToOrder(OrderDto orderDto) {
        Order order = modelMapper.map(orderDto, Order.class);
        return order;
    }

    public OrderDto mapToOrderDto(Order order) {
        OrderDto orderDto = modelMapper.map(order, OrderDto.class);
        return orderDto;
    }

    public List<Order> mapToOrderList(final List<OrderDto> orderDtos) {
        if (orderDtos.isEmpty()) {
            return new ArrayList<>();
        } else {
            return orderDtos.stream()
                    .map(this::mapToOrder)
                    .collect(Collectors.toList());
        }
    }

    public List<OrderDto> mapToOrderDtoList(final List<Order> orders) {
        if (orders.isEmpty()) {
            return new ArrayList<>();
        } else {
            return orders.stream()
                    .map(this::mapToOrderDto)
                    .collect(Collectors.toList());
        }
    }
}
