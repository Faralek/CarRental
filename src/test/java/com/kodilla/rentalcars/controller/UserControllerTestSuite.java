package com.kodilla.rentalcars.controller;

import com.google.gson.Gson;
import com.kodilla.rentalcars.domain.Cart;
import com.kodilla.rentalcars.domain.User;
import com.kodilla.rentalcars.dto.CartDto;
import com.kodilla.rentalcars.dto.UserDto;
import com.kodilla.rentalcars.mapper.UserMapper;
import com.kodilla.rentalcars.service.UserDbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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
@WebMvcTest(UserController.class)
public class UserControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserDbService userDbService;
    @MockBean
    private UserMapper userMapper;

    @Test
    public void shouldCreateEmptyUserList() throws Exception {

        //Given
        List<UserDto> userDtoList = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        when(userDbService.getAllUsers()).thenReturn(userList);
        when(userMapper.mapToUserDtoList(userList)).thenReturn(userDtoList);

        //When & Then
        mockMvc.perform(get("/v1/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

    }

    @Test
    public void shouldGetUserList() throws Exception {
        //Given
        List<UserDto> userDtoList = new ArrayList<>();
        CartDto cartDto = new CartDto();
        userDtoList.add(new UserDto(1L, "Test username", "Test password", cartDto, new ArrayList<>()));
        List<User> userList = new ArrayList<>();
        Cart cart = new Cart();
        userList.add(new User(1L, "Test username", "Test password", cart, new ArrayList<>()));

        when(userDbService.getAllUsers()).thenReturn(userList);
        when(userMapper.mapToUserDtoList(userList)).thenReturn(userDtoList);
        //When & Then
        mockMvc.perform(get("/v1/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].username", is("Test username")))
                .andExpect(jsonPath("$[0].password", is("Test password")))
                .andExpect(jsonPath("$[0].orders", is(new ArrayList<>())));
    }

    @Test
    public void shouldGetUser() throws Exception {

        //Given
        CartDto cartDto = new CartDto();
        Cart cart = new Cart();
        UserDto userDto = new UserDto(1L, "Test username", "Test password", cartDto, new ArrayList<>());
        User user = new User(1L, "Test username", "Test password", cart, new ArrayList<>());

        when(userDbService.getUser(1L)).thenReturn(Optional.of(user));
        when(userMapper.mapToUserDto(any(User.class))).thenReturn(userDto);

        mockMvc.perform(get("/v1/users/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.username", is("Test username")))
                .andExpect(jsonPath("$.password", is("Test password")))
                .andExpect(jsonPath("$.orders", is(new ArrayList<>())));
    }

    @Test
    public void shouldDeleteUser() throws Exception {
        //Given
        Cart cart = new Cart();
        User user = new User(1L, "Test username", "Test password", cart, new ArrayList<>());

        when(userDbService.saveUser(any(User.class))).thenReturn(user);
        userDbService.saveUser(user);

        // When & Then
        mockMvc.perform(delete("/v1/users/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void shouldUpdateUser() throws Exception {
        //Given
        CartDto cartDto = new CartDto();
        Cart cart = new Cart();
        UserDto userDto = new UserDto(1L, "Test username", "Test password", cartDto, new ArrayList<>());
        User user = new User(1L, "Test username", "Test password", cart, new ArrayList<>());

        when(userDbService.saveUser(any(User.class))).thenReturn(user);
        when(userMapper.mapToUser(any(UserDto.class))).thenReturn(user);
        when(userMapper.mapToUserDto(any(User.class))).thenReturn(userDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(userDto);

        //When&Then
        mockMvc.perform(put("/v1/users").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.username", is("Test username")))
                .andExpect(jsonPath("$.password", is("Test password")))
                .andExpect(jsonPath("$.orders", is(new ArrayList<>())));
    }

    @Test
    public void shouldCreateUser() throws Exception {

        //Given
        CartDto cartDto = new CartDto();
        Cart cart = new Cart();
        UserDto userDto = new UserDto(1L, "Test username", "Test password", cartDto, new ArrayList<>());
        User user = new User(1L, "Test username", "Test password", cart, new ArrayList<>());

        when(userDbService.saveUser(any(User.class))).thenReturn(user);
        when(userMapper.mapToUser(any(UserDto.class))).thenReturn(user);
        when(userMapper.mapToUserDto(any(User.class))).thenReturn(userDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(userDto);

        //When & Then

        mockMvc.perform(post("/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());

    }
}