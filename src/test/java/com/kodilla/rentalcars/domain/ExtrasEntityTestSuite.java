package com.kodilla.rentalcars.domain;

import com.kodilla.rentalcars.repository.ExtrasRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExtrasEntityTestSuite {
    @Autowired
    ExtrasRepository extrasRepository;
    @Test
    public void createExtrasTestSuite(){
        //GIVEN
        Extras extras = new Extras();
        Extras extras1 = new Extras();

        //WHEN
        extrasRepository.save(extras);
        extrasRepository.save(extras1);
        int number = extrasRepository.findAll().size();

        //THEN
        Assert.assertEquals(2,number);

        //CLEAN
        extrasRepository.deleteAll();
    }
}
