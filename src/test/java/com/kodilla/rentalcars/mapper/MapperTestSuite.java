package com.kodilla.rentalcars.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MapperTestSuite {

    @InjectMocks
    private CarMapper carMapper;
    @InjectMocks
    private CartMapper cartMapper;
    @InjectMocks
    private ExtrasMapper extrasMapper;
    @InjectMocks
    private OrderMapper orderMapper;
    @InjectMocks
    private UserMapper userMapper;

    @Test
    public void testCarMapper() {

    }
}
