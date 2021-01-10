package com.kodilla.rentalcars.domain;

import com.kodilla.rentalcars.repository.CarRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarEntityTestSuite {
    @Autowired
    CarRepository carRepository;
    @Test
    public void createCarTestSuite(){
        //GIVEN
        Car car = new Car();
        Car car1 = new Car();

        //WHEN
        carRepository.save(car);
        carRepository.save(car1);
        int number = carRepository.findAll().size();

        //THEN
        Assert.assertEquals(2,number);

        //CLEAN
        carRepository.deleteAll();
    }
}
