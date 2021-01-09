package com.kodilla.rentalcars.facade;

import com.kodilla.rentalcars.domain.Order;
import com.kodilla.rentalcars.emailService.EmailService;
import com.kodilla.rentalcars.service.OrderDbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OrderServiceFacade {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceFacade.class);

    @Autowired
    private OrderDbService orderDbService;
    @Autowired
    private EmailService emailService;

    public List<Order> getAllOrders(){
        LOGGER.info(orderDbService.getAllOrders().size() + " Orders in database");
        return orderDbService.getAllOrders();
    }

    public Order saveOrder(final Order order){
        LOGGER.info("Order with id : "+order.getId() + " Added to database");
        emailService.sendNewOrderInformationMail(order.getCars(),order.getUser());
        return orderDbService.saveOrder(order);
    }

    public void deleteId(final Long id){
        LOGGER.info("Order with id : "+ id + " removed from database");
        emailService.sendDeleteOrderInformation(orderDbService.getOrder(id).get().getUser());
        orderDbService.deleteById(id);
    }

    public Optional<Order> getOrder(final Long id){
        if (orderDbService.getOrder(id).isPresent()){
            LOGGER.info("Order found!");
        }else {
            LOGGER.info("Order not found");
        }
        return orderDbService.getOrder(id);
    }

}
