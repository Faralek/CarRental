package com.kodilla.rentalcars.controller;

import com.google.gson.Gson;
import com.kodilla.rentalcars.domain.Cart;
import com.kodilla.rentalcars.domain.User;
import com.kodilla.rentalcars.dto.CartDto;
import com.kodilla.rentalcars.dto.UserDto;
import com.kodilla.rentalcars.mapper.CarMapper;
import com.kodilla.rentalcars.mapper.CartMapper;
import com.kodilla.rentalcars.service.CartDbService;
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
@WebMvcTest(CartController.class)
public class CartControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CartDbService cartDbService;
    @MockBean
    private CartMapper cartMapper;
    @MockBean
    private CarMapper carMapper; // <--- is not used directly, but without it CartController.class wont build

    @Test
    public void shouldCreateEmptyCartList() throws Exception {

        //Given
        List<CartDto> cartDtoList = new ArrayList<>();
        List<Cart> cartList = new ArrayList<>();
        when(cartDbService.getAllCarts()).thenReturn(cartList);
        when(cartMapper.mapToCartDtoList(cartList)).thenReturn(cartDtoList);

        //When & Then
        mockMvc.perform(get("/v1/carts").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

    }

    @Test
    public void shouldGetCartList() throws Exception {
        //Given
        List<CartDto> cartDtoList = new ArrayList<>();
        UserDto userDto = new UserDto();
        cartDtoList.add(new CartDto(1L, new BigDecimal(0), new ArrayList<>(), new ArrayList<>(), userDto));

        List<Cart> cartList = new ArrayList<>();
        User user = new User();
        cartList.add(new Cart(1L, new BigDecimal(0), new ArrayList<>(), new ArrayList<>(), user));

        when(cartDbService.getAllCarts()).thenReturn(cartList);
        when(cartMapper.mapToCartDtoList(cartList)).thenReturn(cartDtoList);

        //When & Then
        mockMvc.perform(get("/v1/carts").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].cars", is(new ArrayList<>())))
                .andExpect(jsonPath("$[0].orders", is(new ArrayList<>())));
    }

    @Test
    public void shouldGetCart() throws Exception {

        //Given
        UserDto userDto = new UserDto();
        CartDto cartDto = new CartDto(1L, new BigDecimal(0), new ArrayList<>(), new ArrayList<>(), userDto);

        User user = new User();
        Cart cart = new Cart(1L, new BigDecimal(0), new ArrayList<>(), new ArrayList<>(), user);

        when(cartDbService.getCartById(1L)).thenReturn(Optional.of(cart));
        when(cartMapper.mapToCartDto(any(Cart.class))).thenReturn(cartDto);

        mockMvc.perform(get("/v1/carts/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.cars", is(new ArrayList<>())))
                .andExpect(jsonPath("$.orders", is(new ArrayList<>())));
    }

    @Test
    public void shouldDeleteCart() throws Exception {
        //Given
        User user = new User();
        Cart cart = new Cart(1L, new BigDecimal(0), new ArrayList<>(), new ArrayList<>(), user);

        when(cartDbService.saveCart(any(Cart.class))).thenReturn(cart);
        cartDbService.saveCart(cart);

        // When & Then
        mockMvc.perform(delete("/v1/carts/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void shouldUpdateCart() throws Exception {
        //Given
        UserDto userDto = new UserDto();
        CartDto cartDto = new CartDto(1L, new BigDecimal(0), new ArrayList<>(), new ArrayList<>(), userDto);

        User user = new User();
        Cart cart = new Cart(1L, new BigDecimal(0), new ArrayList<>(), new ArrayList<>(), user);

        when(cartDbService.saveCart(any(Cart.class))).thenReturn(cart);
        when(cartMapper.mapToCart(any(CartDto.class))).thenReturn(cart);
        when(cartMapper.mapToCartDto(any(Cart.class))).thenReturn(cartDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(cartDto);

        //When&Then
        mockMvc.perform(put("/v1/carts").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.cars", is(new ArrayList<>())))
                .andExpect(jsonPath("$.orders", is(new ArrayList<>())));
    }

    @Test
    public void shouldCreateCart() throws Exception {

        //Given
        UserDto userDto = new UserDto();
        CartDto cartDto = new CartDto(1L, new BigDecimal(0), new ArrayList<>(), new ArrayList<>(), userDto);

        User user = new User();
        Cart cart = new Cart(1L, new BigDecimal(0), new ArrayList<>(), new ArrayList<>(), user);

        when(cartDbService.saveCart(any(Cart.class))).thenReturn(cart);
        when(cartMapper.mapToCart(any(CartDto.class))).thenReturn(cart);
        when(cartMapper.mapToCartDto(any(Cart.class))).thenReturn(cartDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(cartDto);

        //When & Then
        mockMvc.perform(post("/v1/carts")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());

    }
}
