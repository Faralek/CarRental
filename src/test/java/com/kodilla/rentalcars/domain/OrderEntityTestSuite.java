package com.kodilla.rentalcars.domain;

import com.kodilla.rentalcars.repository.OrderRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderEntityTestSuite {

    @Autowired
    OrderRepository orderRepository;

    @Test
    public void createOrderTestSuite(){
        //GIVEN
        Order order = new Order();
        Order order1 = new Order();

        //WHEN
        orderRepository.save(order);
        orderRepository.save(order1);
        int number = orderRepository.findAll().size();

        //THEN
        Assert.assertEquals(2,number);

        //CLEAN
        orderRepository.deleteAll();
    }
}
