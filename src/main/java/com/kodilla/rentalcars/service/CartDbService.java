package com.kodilla.rentalcars.service;

import com.kodilla.rentalcars.domain.Cart;
import com.kodilla.rentalcars.log.Log;
import com.kodilla.rentalcars.log.LogService;
import com.kodilla.rentalcars.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CartDbService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private LogService logService;

    public List<Cart> getAllCarts(){
        return cartRepository.findAll();
    }

    public Cart saveCart(final Cart cart){
        logService.saveLog(new Log("Created new cart" , LocalDate.now()));
        return cartRepository.save(cart);
    }

    public void deleteById(final Long id){
        logService.saveLog(new Log("Deleted cart" , LocalDate.now()));
        cartRepository.deleteById(id);
    }

    public Optional<Cart> getCartById(final Long id){
        return cartRepository.findById(id);
    }

}
