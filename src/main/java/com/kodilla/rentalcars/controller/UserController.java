package com.kodilla.rentalcars.controller;

import com.kodilla.rentalcars.dto.UserDto;
import com.kodilla.rentalcars.exception.UserNotFoundException;
import com.kodilla.rentalcars.mapper.UserMapper;
import com.kodilla.rentalcars.service.UserDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1")
public class UserController {
    @Autowired
    private UserDbService userDbService;
    @Autowired
    private UserMapper userMapper;

    @RequestMapping(method = RequestMethod.POST, value = "/users", consumes = APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto) {
        userDbService.saveUser(userMapper.mapToUser(userDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/users")
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return userMapper.mapToUserDto(userDbService.saveUser(userMapper.mapToUser(userDto)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public List<UserDto> getUserList() {
        return userMapper.mapToUserDtoList(userDbService.getAllUsers());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}")
    public UserDto getUser(@PathVariable Long userId) throws UserNotFoundException {
        return userMapper.mapToUserDto(userDbService.getUser(userId).orElseThrow(() -> new UserNotFoundException("Extras not found " + userId)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{userId}")
    public void deleteUser(@PathVariable Long userId) throws UserNotFoundException {
        try {
            userDbService.deleteById(userId);
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException("Car not found " + userId, e);
        }
    }
}
