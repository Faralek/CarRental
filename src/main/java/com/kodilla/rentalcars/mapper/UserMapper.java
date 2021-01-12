package com.kodilla.rentalcars.mapper;

import com.kodilla.rentalcars.domain.User;
import com.kodilla.rentalcars.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public User mapToUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        return user;
    }

    public UserDto mapToUserDto(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }

    public List<UserDto> mapToUserDtoList(final List<User> users) {
        if (users.isEmpty()) {
            return new ArrayList<>();
        } else {
            return users.stream()
                    .map(this::mapToUserDto)
                    .collect(Collectors.toList());
        }
    }

}