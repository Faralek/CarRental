package com.kodilla.rentalcars.service;

import com.kodilla.rentalcars.domain.Order;
import com.kodilla.rentalcars.log.Log;
import com.kodilla.rentalcars.log.LogService;
import com.kodilla.rentalcars.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrderDbService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private LogService logService;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order saveOrder(final Order order) {
        logService.saveLog(new Log("Created new Order" , LocalDate.now()));
        return orderRepository.save(order);
    }

    public void deleteById(final Long id) {
        logService.saveLog(new Log("Deleted order" , LocalDate.now()));
        orderRepository.deleteById(id);
    }

    public Optional<Order> getOrder(final Long id) {
        return orderRepository.findById(id);
    }
}
