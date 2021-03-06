package com.kodilla.rentalcars.mapper;

import com.kodilla.rentalcars.domain.Cart;
import com.kodilla.rentalcars.domain.Order;
import com.kodilla.rentalcars.domain.User;
import com.kodilla.rentalcars.dto.CartDto;
import com.kodilla.rentalcars.dto.OrderDto;
import com.kodilla.rentalcars.dto.UserDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class OrderMapperTestSuite {

    @InjectMocks
    private OrderMapper orderMapper;

    @Test
    public void testOrderMapper() {
        //Given
        CartDto cartDto = new CartDto();
        UserDto userDto = new UserDto();
        OrderDto orderDto = new OrderDto(1L, cartDto, new ArrayList<>(), new BigDecimal(0));
        OrderDto orderDto1;

        Cart cart = new Cart();
        User user = new User();
        Order order = new Order(1L, cart, new ArrayList<>(), new BigDecimal(0));
        Order order1;

        List<OrderDto> orderDtoList = new ArrayList<>();
        List<OrderDto> orderDtoList1;
        List<Order> orderList = new ArrayList<>();
        List<Order> orderList1;

        //WHEN
        order1 = orderMapper.mapToOrder(orderDto);
        orderDto1 = orderMapper.mapToOrderDto(order);

        orderDtoList.add(orderDto);
        orderList.add(order);

        orderDtoList1 = orderMapper.mapToOrderDtoList(orderList);
        orderList1 = orderMapper.mapToOrderList(orderDtoList);

        //THEN
        Assert.assertEquals(orderDtoList1.size(),orderList.size());
        Assert.assertEquals(orderList1.size(),orderList.size());
    }
}