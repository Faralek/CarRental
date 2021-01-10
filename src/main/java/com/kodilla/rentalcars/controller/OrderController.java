package com.kodilla.rentalcars.controller;

import com.kodilla.rentalcars.dto.OrderDto;
import com.kodilla.rentalcars.exception.OrderNotFoundException;
import com.kodilla.rentalcars.facade.OrderServiceFacade;
import com.kodilla.rentalcars.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/order")
public class OrderController {
    @Autowired
    private OrderServiceFacade orderServiceFacade;
    @Autowired
    private OrderMapper orderMapper;

    @RequestMapping(method = RequestMethod.POST, value = "createOrder", consumes = APPLICATION_JSON_VALUE)
    public void createOrder(@RequestBody OrderDto orderDto) {
        orderServiceFacade.saveOrder(orderMapper.mapToOrder(orderDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateOrder")
    public OrderDto updateCar(@RequestBody OrderDto orderDto) {
        return orderMapper.mapToOrderDto(orderServiceFacade.saveOrder(orderMapper.mapToOrder(orderDto)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getOrderList")
    public List<OrderDto> getOrderList() {
        return orderMapper.mapToOrderDtoList(orderServiceFacade.getAllOrders());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getOrder")
    public OrderDto getOrder(@RequestParam Long orderId) throws OrderNotFoundException {
        return orderMapper.mapToOrderDto(orderServiceFacade.getOrder(orderId).orElseThrow(() -> new OrderNotFoundException("Extras not found " + orderId)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteOrder")
    public void deleteOrder(@RequestParam Long orderId) throws OrderNotFoundException {
        try {
            orderServiceFacade.deleteId(orderId);
        } catch (EmptyResultDataAccessException e) {
            throw new OrderNotFoundException("Car not found " + orderId, e);
        }
    }
}
