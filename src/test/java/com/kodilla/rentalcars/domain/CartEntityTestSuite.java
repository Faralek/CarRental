package com.kodilla.rentalcars.domain;

import com.kodilla.rentalcars.repository.CartRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartEntityTestSuite {
    @Autowired
    CartRepository cartRepository;
    @Test
    public void createCartTestSuite(){
        //GIVEN
        cartRepository.deleteAll();

        Cart cart = new Cart();
        Cart cart1 = new Cart();

        //WHEN
        cartRepository.save(cart);
        cartRepository.save(cart1);
        int number = cartRepository.findAll().size();

        //THEN
        Assert.assertEquals(2,number);

        //CLEAN
        cartRepository.deleteAll();
    }
}
