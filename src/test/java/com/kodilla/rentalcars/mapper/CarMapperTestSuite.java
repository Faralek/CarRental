package com.kodilla.rentalcars.mapper;

import com.kodilla.rentalcars.domain.Car;
import com.kodilla.rentalcars.domain.Cart;
import com.kodilla.rentalcars.domain.Order;
import com.kodilla.rentalcars.dto.CarDto;
import com.kodilla.rentalcars.dto.CartDto;
import com.kodilla.rentalcars.dto.OrderDto;
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
public class CarMapperTestSuite {

    @InjectMocks
    private CarMapper carMapper;
    @Mock
    private ExtrasMapper extrasMapper;
    @Mock
    private CartMapper cartMapper;
    @Mock
    private OrderMapper orderMapper;

    @Test
    public void testCarMapper() {
        //Given
        OrderDto orderDto = new OrderDto();
        CartDto cartDto = new CartDto();
        CarDto carDto = new CarDto(1L, "description", "name", new BigDecimal(0), new ArrayList<>(), cartDto, orderDto);


        Cart cart = new Cart();
        Order order = new Order();
        Car car = new Car(1L, "description", "name", new BigDecimal(0), new ArrayList<>(), cart, order);

        List<CarDto> carDtoList = new ArrayList<>();
        List<CarDto> carDtoList1;
        List<Car> carList = new ArrayList<>();
        List<Car> carList1;

        carDtoList.add(carDto);
        carList.add(car);

        when(extrasMapper.mapToExtrasDtoList(new ArrayList<>())).thenReturn(new ArrayList<>());
        when(cartMapper.mapToCartDto(any(Cart.class))).thenReturn(cartDto);
        when(orderMapper.mapToOrderDto(any(Order.class))).thenReturn(orderDto);

        when(extrasMapper.mapToExtrasList(new ArrayList<>())).thenReturn(new ArrayList<>());
        when(cartMapper.mapToCart(any(CartDto.class))).thenReturn(cart);
        when(orderMapper.mapToOrder(any(OrderDto.class))).thenReturn(order);

        //WHEN
        CarDto carDto1 = carMapper.mapToCarDto(car);
        Car car1 = carMapper.mapToCar(carDto);
        carList1 = carMapper.mapToCarList(carDtoList);
        carDtoList1 = carMapper.mapToCarDtoList(carList);

        //THEN
        assertThat(carDto).isEqualToComparingFieldByField(carDto1);
        assertThat(car).isEqualToComparingFieldByField(car1);
        Assert.assertEquals(carDtoList.size(),carDtoList1.size());
        Assert.assertEquals(carList.size(),carList1.size());

    }

}
