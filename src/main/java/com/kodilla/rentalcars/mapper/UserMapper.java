package com.kodilla.rentalcars.mapper;

import com.kodilla.rentalcars.domain.User;
import com.kodilla.rentalcars.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private OrderMapper orderMapper;

    public User mapToUser(UserDto userDto) {
        return new User(userDto.getId(),
                userDto.getUsername(),
                userDto.getPassword(),
                cartMapper.mapToCart(userDto.getCart()),
                orderMapper.mapToOrderList(userDto.getOrders()));
    }

    public UserDto mapToUserDto(User user) {
        return new UserDto(user.getId(),
                user.getUsername(),
                user.getPassword(),
                cartMapper.mapToCartDto(user.getCart()),
                orderMapper.mapToOrderDtoList(user.getOrders()));
    }

    public List<User> mapToUserList(List<UserDto> userDtoList) {
        return userDtoList.stream()
                .map(u -> new User(u.getId(),
                        u.getUsername(),
                        u.getPassword(),
                        cartMapper.mapToCart(u.getCart()),
                        orderMapper.mapToOrderList(u.getOrders())))
                .collect(Collectors.toList());
    }

    public List<UserDto> mapToUserDtoList(List<User> users) {
        return users.stream()
                .map(u -> new UserDto(u.getId(),
                        u.getUsername(),
                        u.getPassword(),
                        cartMapper.mapToCartDto(u.getCart()),
                        orderMapper.mapToOrderDtoList(u.getOrders())))
                .collect(Collectors.toList());
    }
}
