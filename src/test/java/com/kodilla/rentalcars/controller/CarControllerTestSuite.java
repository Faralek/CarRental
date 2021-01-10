package com.kodilla.rentalcars.controller;

import com.google.gson.Gson;
import com.kodilla.rentalcars.domain.Car;
import com.kodilla.rentalcars.domain.Cart;
import com.kodilla.rentalcars.domain.Order;
import com.kodilla.rentalcars.dto.CarDto;
import com.kodilla.rentalcars.dto.CartDto;
import com.kodilla.rentalcars.dto.OrderDto;
import com.kodilla.rentalcars.mapper.CarMapper;
import com.kodilla.rentalcars.mapper.ExtrasMapper;
import com.kodilla.rentalcars.service.CarDbService;
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
@WebMvcTest(CarController.class)
public class CarControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CarDbService carDbService;
    @MockBean
    private CarMapper carMapper;
    @MockBean
    private ExtrasMapper extrasMapper;

    @Test
    public void shouldCreateEmptyUserList() throws Exception {

        //Given
        List<CarDto> carDtoList = new ArrayList<>();
        List<Car> carList = new ArrayList<>();
        when(carDbService.getAllCars()).thenReturn(carList);
        when(carMapper.mapToCarDtoList(carList)).thenReturn(carDtoList);

        //When & Then
        mockMvc.perform(get("/v1/cars").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

    }

    @Test
    public void shouldGetUserList() throws Exception {
        //Given
        List<CarDto> carDtoList = new ArrayList<>();
        OrderDto orderDto = new OrderDto();
        CartDto cartDto = new CartDto();
        carDtoList.add(new CarDto(1L, "description", "name", new BigDecimal(0), new ArrayList<>(), cartDto, orderDto));

        List<Car> carList = new ArrayList<>();
        Cart cart = new Cart();
        Order order = new Order();
        carList.add(new Car(1L, "description", "name", new BigDecimal(0), new ArrayList<>(), cart, order));

        when(carDbService.getAllCars()).thenReturn(carList);
        when(carMapper.mapToCarDtoList(carList)).thenReturn(carDtoList);
        //When & Then
        mockMvc.perform(get("/v1/cars").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("name")))
                .andExpect(jsonPath("$[0].description", is("description")))
                .andExpect(jsonPath("$[0].extrasList", is(new ArrayList<>())));
    }

    @Test
    public void shouldGetUser() throws Exception {

        //Given
        OrderDto orderDto = new OrderDto();
        CartDto cartDto = new CartDto();
        CarDto carDto = new CarDto(1L, "description", "name", new BigDecimal(0), new ArrayList<>(), cartDto, orderDto);

        Cart cart = new Cart();
        Order order = new Order();
        Car car = new Car(1L, "description", "name", new BigDecimal(0), new ArrayList<>(), cart, order);

        when(carDbService.getCarById(1L)).thenReturn(Optional.of(car));
        when(carMapper.mapToCarDto(any(Car.class))).thenReturn(carDto);

        mockMvc.perform(get("/v1/cars/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("name")))
                .andExpect(jsonPath("$.description", is("description")))
                .andExpect(jsonPath("$.extrasList", is(new ArrayList<>())));
    }

    @Test
    public void shouldDeleteUser() throws Exception {
        //Given
        Cart cart = new Cart();
        Order order = new Order();
        Car car = new Car(1L, "description", "name", new BigDecimal(0), new ArrayList<>(), cart, order);

        when(carDbService.saveCar(any(Car.class))).thenReturn(car);
        carDbService.saveCar(car);

        // When & Then
        mockMvc.perform(delete("/v1/cars/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void shouldUpdateUser() throws Exception {
        //Given
        OrderDto orderDto = new OrderDto();
        CartDto cartDto = new CartDto();
        CarDto carDto = new CarDto(1L, "description", "name", new BigDecimal(0), new ArrayList<>(), cartDto, orderDto);

        Cart cart = new Cart();
        Order order = new Order();
        Car car = new Car(1L, "description", "name", new BigDecimal(0), new ArrayList<>(), cart, order);

        when(carDbService.saveCar(any(Car.class))).thenReturn(car);
        when(carMapper.mapToCar(any(CarDto.class))).thenReturn(car);
        when(carMapper.mapToCarDto(any(Car.class))).thenReturn(carDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(carDto);

        //When&Then
        mockMvc.perform(put("/v1/cars").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("name")))
                .andExpect(jsonPath("$.description", is("description")))
                .andExpect(jsonPath("$.extrasList", is(new ArrayList<>())));
    }

    @Test
    public void shouldCreateUser() throws Exception {

        //Given
        OrderDto orderDto = new OrderDto();
        CartDto cartDto = new CartDto();
        CarDto carDto = new CarDto(1L, "description", "name", new BigDecimal(0), new ArrayList<>(), cartDto, orderDto);

        Cart cart = new Cart();
        Order order = new Order();
        Car car = new Car(1L, "description", "name", new BigDecimal(0), new ArrayList<>(), cart, order);

        when(carDbService.saveCar(any(Car.class))).thenReturn(car);
        when(carMapper.mapToCar(any(CarDto.class))).thenReturn(car);
        when(carMapper.mapToCarDto(any(Car.class))).thenReturn(carDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(carDto);

        //When & Then

        mockMvc.perform(post("/v1/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());

    }
}
