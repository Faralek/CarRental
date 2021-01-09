package com.kodilla.rentalcars.controller;

import com.kodilla.rentalcars.dto.CartDto;
import com.kodilla.rentalcars.exception.CartNotFoundException;
import com.kodilla.rentalcars.mapper.CartMapper;
import com.kodilla.rentalcars.service.CartDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/cart")
public class CartController {

    @Autowired
    private CartDbService cartDbService;
    @Autowired
    private CartMapper cartMapper;

    @RequestMapping(method = RequestMethod.POST, value = "createCart", consumes = APPLICATION_JSON_VALUE)
    public void createCart(@RequestBody CartDto cartDto) {
        cartDbService.saveCart(cartMapper.mapToCart(cartDto));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getCarts")
    public List<CartDto> getCarts() {
        return cartMapper.mapToCartDtoList(cartDbService.getAllCarts());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getCart")
    public CartDto getCart(@RequestParam Long cartId) throws CartNotFoundException {
        return cartMapper.mapToCartDto(cartDbService.getCartById(cartId).orElseThrow(() -> new CartNotFoundException("Cart not found " + cartId)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteCart")
    public void deleteCar(@RequestParam Long cartId) throws CartNotFoundException {
        try {
            cartDbService.deleteById(cartId);
        } catch (EmptyResultDataAccessException e) {
            throw new CartNotFoundException("Car not found " + cartId, e);
        }
    }
}
