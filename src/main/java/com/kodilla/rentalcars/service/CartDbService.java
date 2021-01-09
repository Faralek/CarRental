package com.kodilla.rentalcars.service;

import com.kodilla.rentalcars.domain.Cart;
import com.kodilla.rentalcars.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartDbService {
    @Autowired
    private CartRepository cartRepository;

    public List<Cart> getAllCarts(){
        return cartRepository.findAll();
    }

    public Cart saveCart(final Cart cart){
        return cartRepository.save(cart);
    }

    public void deleteById(final Long id){
        cartRepository.deleteById(id);
    }

    public Optional<Cart> getCartById(final Long id){
        return cartRepository.findById(id);
    }

}
