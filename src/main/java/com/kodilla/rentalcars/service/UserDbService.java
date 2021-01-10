package com.kodilla.rentalcars.service;

import com.kodilla.rentalcars.domain.User;
import com.kodilla.rentalcars.log.Log;
import com.kodilla.rentalcars.log.LogService;
import com.kodilla.rentalcars.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserDbService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LogService logService;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(final User user) {
        logService.saveLog(new Log("Created new user " + user.getUsername(), LocalDate.now()));
        return userRepository.save(user);
    }

    public void deleteById(final Long id) {
        logService.saveLog(new Log("Deleted user ", LocalDate.now()));
        userRepository.deleteById(id);
    }

    public Optional<User> getUser(final Long id) {
        return userRepository.findById(id);
    }
}
