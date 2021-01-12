package com.kodilla.rentalcars.mapper;

import com.kodilla.rentalcars.domain.Cart;
import com.kodilla.rentalcars.domain.User;
import com.kodilla.rentalcars.dto.CartDto;
import com.kodilla.rentalcars.dto.UserDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserMapperTestSuite {
    @InjectMocks
    private UserMapper userMapper;
    @Mock
    private CartMapper cartMapper;
    @Mock
    private OrderMapper orderMapper;

    @Test
    public void testUserMapper() {
        //Given
        CartDto cartDto = new CartDto();
        UserDto userDto = new UserDto(1L, "Test username", "Test password", cartDto, new ArrayList<>());
        UserDto userDto1;

        Cart cart = new Cart();
        User user = new User(1L, "Test username", "Test password", cart, new ArrayList<>());
        User user1;

        List<UserDto> userDtoList = new ArrayList<>();
        List<UserDto> userDtoList1;
        List<User> userList = new ArrayList<>();

        //WHEN
        user1 = userMapper.mapToUser(userDto);
        userDto1 = userMapper.mapToUserDto(user);

        userDtoList.add(userDto);
        userList.add(user);

        userDtoList1 = userMapper.mapToUserDtoList(userList);

        //THEN
        Assert.assertEquals(userDtoList.size(),userDtoList1.size());
    }
}
