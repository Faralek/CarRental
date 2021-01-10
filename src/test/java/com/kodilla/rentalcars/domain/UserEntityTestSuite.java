package com.kodilla.rentalcars.domain;

import com.kodilla.rentalcars.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserEntityTestSuite {

    @Autowired
    UserRepository userRepository;

    @Test
    public void createUserTestSuite(){
        //GIVEN
        User user = new User();
        User user1 = new User();

        //WHEN
        userRepository.save(user);
        userRepository.save(user1);
        int number = userRepository.findAll().size();

        //THEN
        Assert.assertEquals(2,number);
        //CLEAN
        userRepository.deleteAll();
    }
}
