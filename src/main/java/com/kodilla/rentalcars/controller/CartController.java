package com.kodilla.rentalcars.controller;

import com.kodilla.rentalcars.domain.Car;
import com.kodilla.rentalcars.domain.Cart;
import com.kodilla.rentalcars.dto.CarDto;
import com.kodilla.rentalcars.dto.CartDto;
import com.kodilla.rentalcars.exception.CartNotFoundException;
import com.kodilla.rentalcars.mapper.CarMapper;
import com.kodilla.rentalcars.mapper.CartMapper;
import com.kodilla.rentalcars.service.CartDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1")
public class CartController {

    @Autowired
    private CartDbService cartDbService;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private CarMapper carMapper;

    @RequestMapping(method = RequestMethod.POST, value = "/carts", consumes = APPLICATION_JSON_VALUE)
    public void createCart(@RequestBody CartDto cartDto) {
        cartDbService.saveCart(cartMapper.mapToCart(cartDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/carts")
    public CartDto updateCart(@RequestBody CartDto cartDto) {
        return cartMapper.mapToCartDto(cartDbService.saveCart(cartMapper.mapToCart(cartDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/carts/addCar", consumes = APPLICATION_JSON_VALUE)
    public Cart addCar(@RequestParam int duration, @RequestBody CarDto carDto, @RequestParam Long id) {
        Cart cart = cartDbService.getCartById(id).get();
        Car car = carMapper.mapToCar(carDto);
        cart.addCar(car, duration);
        cartDbService.saveCart(cart);
        return cart;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/carts/removeCar")
    public void removeCar(@RequestParam int duration, @RequestBody CarDto carDto, @RequestParam Long id) {
        Cart cart = cartDbService.getCartById(id).get();
        cartDbService.getCartById(id).get().removeCar(carMapper.mapToCar(carDto), duration);
        cartDbService.saveCart(cart);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/carts")
    public List<CartDto> getCarts() {
        return cartMapper.mapToCartDtoList(cartDbService.getAllCarts());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/carts/{cartId}")
    public CartDto getCart(@PathVariable Long cartId) throws CartNotFoundException {
        Optional<Cart> cart = cartDbService.getCartById(cartId);
        System.out.println(cart.get().getCars().size());
        return cartMapper.mapToCartDto(cartDbService.getCartById(cartId).orElseThrow(() -> new CartNotFoundException("Cart not found " + cartId)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/carts/{cartId}")
    public void deleteCar(@PathVariable Long cartId) throws CartNotFoundException {
        try {
            cartDbService.deleteById(cartId);
        } catch (EmptyResultDataAccessException e) {
            throw new CartNotFoundException("Car not found " + cartId, e);
        }
    }
}
