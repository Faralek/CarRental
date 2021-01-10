package com.kodilla.rentalcars.controller;

import com.google.gson.Gson;
import com.kodilla.rentalcars.domain.Cart;
import com.kodilla.rentalcars.domain.Order;
import com.kodilla.rentalcars.domain.User;
import com.kodilla.rentalcars.dto.CartDto;
import com.kodilla.rentalcars.dto.OrderDto;
import com.kodilla.rentalcars.dto.UserDto;
import com.kodilla.rentalcars.facade.OrderServiceFacade;
import com.kodilla.rentalcars.mapper.OrderMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private OrderServiceFacade orderServiceFacade;
    @MockBean
    private OrderMapper orderMapper;

    @Test
    public void shouldCreateEmptyOrderList() throws Exception {

        //Given
        List<OrderDto> orderDtoList = new ArrayList<>();
        List<Order> orderList = new ArrayList<>();
        when(orderServiceFacade.getAllOrders()).thenReturn(orderList);
        when(orderMapper.mapToOrderDtoList(orderList)).thenReturn(orderDtoList);

        //When & Then
        mockMvc.perform(get("/v1/orders").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

    }

    @Test
    public void shouldGetOrderList() throws Exception {
        //Given
        List<OrderDto> orderDtoList = new ArrayList<>();
        CartDto cartDto = new CartDto();
        UserDto userDto = new UserDto();
        orderDtoList.add(new OrderDto(1L, cartDto, new ArrayList<>(), userDto, new BigDecimal(0)));

        List<Order> orderList = new ArrayList<>();
        Cart cart = new Cart();
        User user = new User();
        orderList.add(new Order(1L, cart, new ArrayList<>(), user ,new BigDecimal(0)));

        when(orderServiceFacade.getAllOrders()).thenReturn(orderList);
        when(orderMapper.mapToOrderDtoList(orderList)).thenReturn(orderDtoList);

        //When & Then
        mockMvc.perform(get("/v1/orders").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].cars", is(new ArrayList<>())));
    }
    @Test
    public void shouldGetOrder() throws Exception {

        //Given
        CartDto cartDto = new CartDto();
        UserDto userDto = new UserDto();
        OrderDto orderDto = new OrderDto(1L, cartDto, new ArrayList<>(), userDto, new BigDecimal(0));

        Cart cart = new Cart();
        User user = new User();
        Order order = new Order(1L, cart, new ArrayList<>(), user , new BigDecimal(0));

        when(orderServiceFacade.getOrder(1L)).thenReturn(Optional.of(order));
        when(orderMapper.mapToOrderDto(any(Order.class))).thenReturn(orderDto);

        mockMvc.perform(get("/v1/orders/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.cars", is(new ArrayList<>())));
    }

    @Test
    public void shouldDeleteOrder() throws Exception {
        //Given
        Cart cart = new Cart();
        User user = new User();
        Order order = new Order(1L, cart, new ArrayList<>(), user , new BigDecimal(0));

        when(orderServiceFacade.saveOrder(any(Order.class))).thenReturn(order);
        orderServiceFacade.saveOrder(order);

        // When & Then
        mockMvc.perform(delete("/v1/orders/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void shouldUpdateOrder() throws Exception {
        //Given
        CartDto cartDto = new CartDto();
        UserDto userDto = new UserDto();
        OrderDto orderDto = new OrderDto(1L, cartDto, new ArrayList<>(), userDto, new BigDecimal(0));

        Cart cart = new Cart();
        User user = new User();
        Order order = new Order(1L, cart, new ArrayList<>(), user , new BigDecimal(0));

        when(orderServiceFacade.saveOrder(any(Order.class))).thenReturn(order);
        when(orderMapper.mapToOrder(any(OrderDto.class))).thenReturn(order);
        when(orderMapper.mapToOrderDto(any(Order.class))).thenReturn(orderDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(orderDto);

        //When&Then
        mockMvc.perform(put("/v1/orders").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.cars", is(new ArrayList<>())));
    }

    @Test
    public void shouldCreateOrder() throws Exception {

        //Given
        CartDto cartDto = new CartDto();
        UserDto userDto = new UserDto();
        OrderDto orderDto = new OrderDto(1L, cartDto, new ArrayList<>(), userDto, new BigDecimal(0));

        Cart cart = new Cart();
        User user = new User();
        Order order = new Order(1L, cart, new ArrayList<>(), user , new BigDecimal(0));

        when(orderServiceFacade.saveOrder(any(Order.class))).thenReturn(order);
        when(orderMapper.mapToOrder(any(OrderDto.class))).thenReturn(order);
        when(orderMapper.mapToOrderDto(any(Order.class))).thenReturn(orderDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(orderDto);

        //When & Then
        mockMvc.perform(post("/v1/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());

    }
}
