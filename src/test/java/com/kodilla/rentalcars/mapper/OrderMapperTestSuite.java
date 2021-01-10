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
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderMapperTestSuite {

    @InjectMocks
    private OrderMapper orderMapper;
    @Mock
    private CartMapper cartMapper;
    @Mock
    private CarMapper carMapper;
    @Mock
    private UserMapper userMapper;

    @Test
    public void testOrderMapper() {
        //Given
        CartDto cartDto = new CartDto();
        UserDto userDto = new UserDto();
        OrderDto orderDto = new OrderDto(1L, cartDto, new ArrayList<>(), userDto, new BigDecimal(0));
        OrderDto orderDto1;

        Cart cart = new Cart();
        User user = new User();
        Order order = new Order(1L, cart, new ArrayList<>(), user , new BigDecimal(0));
        Order order1;

        List<OrderDto> orderDtoList = new ArrayList<>();
        List<OrderDto> orderDtoList1;
        List<Order> orderList = new ArrayList<>();
        List<Order> orderList1;

        when(cartMapper.mapToCartDto(any(Cart.class))).thenReturn(cartDto);
        when(cartMapper.mapToCart(any(CartDto.class))).thenReturn(cart);
        when(carMapper.mapToCarList(new ArrayList<>())).thenReturn(new ArrayList<>());
        when(carMapper.mapToCarDtoList(new ArrayList<>())).thenReturn(new ArrayList<>());
        when(userMapper.mapToUser(any(UserDto.class))).thenReturn(user);
        when(userMapper.mapToUserDto(any(User.class))).thenReturn(userDto);

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
        assertThat(order).isEqualToComparingFieldByField(order1);
        assertThat(orderDto).isEqualToComparingFieldByField(orderDto1);
    }
}