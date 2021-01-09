package com.kodilla.rentalcars.controller;

import com.kodilla.rentalcars.dto.CarDto;
import com.kodilla.rentalcars.dto.OrderDto;
import com.kodilla.rentalcars.exception.OrderNotFoundException;
import com.kodilla.rentalcars.mapper.OrderMapper;
import com.kodilla.rentalcars.service.OrderDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/order")
public class OrderController {
    @Autowired
    private OrderDbService orderDbService;
    @Autowired
    private OrderMapper orderMapper;

    @RequestMapping(method = RequestMethod.POST, value = "createOrder", consumes = APPLICATION_JSON_VALUE)
    public void createOrder(@RequestBody OrderDto orderDto) {
        orderDbService.saveOrder(orderMapper.mapToOrder(orderDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateOrder")
    public OrderDto updateCar(@RequestBody OrderDto orderDto) {
        return orderMapper.mapToOrderDto(orderDbService.saveOrder(orderMapper.mapToOrder(orderDto)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getOrderList")
    public List<OrderDto> getOrderList() {
        return orderMapper.mapToOrderDtoList(orderDbService.getAllOrders());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getOrder")
    public OrderDto getOrder(@RequestParam Long orderId) throws OrderNotFoundException {
        return orderMapper.mapToOrderDto(orderDbService.getOrder(orderId).orElseThrow(() -> new OrderNotFoundException("Extras not found " + orderId)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteOrder")
    public void deleteOrder(@RequestParam Long orderId) throws OrderNotFoundException {
        try {
            orderDbService.deleteById(orderId);
        } catch (EmptyResultDataAccessException e) {
            throw new OrderNotFoundException("Car not found " + orderId, e);
        }
    }
}
