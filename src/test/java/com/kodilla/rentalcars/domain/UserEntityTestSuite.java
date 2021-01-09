package com.kodilla.rentalcars.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserEntityTestSuite {

    @Test
    public void alwaysAcceptedTest(){
        Assert.assertNotNull(1);
    }
}
