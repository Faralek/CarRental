package com.kodilla.rentalcars.mapper;

import com.kodilla.rentalcars.domain.Cart;
import com.kodilla.rentalcars.domain.User;
import com.kodilla.rentalcars.dto.CartDto;
import com.kodilla.rentalcars.dto.UserDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class CartMapperTestSuite {

    @InjectMocks
    private CartMapper cartMapper;

    @Test
    public void testCartMapper() {
        //GIVEN
        UserDto userDto = new UserDto();
        CartDto cartDto = new CartDto(1L, new BigDecimal(0), new ArrayList<>(), new ArrayList<>(), userDto);
        CartDto cartDto1;

        User user = new User();
        Cart cart = new Cart(1L, new BigDecimal(0), new ArrayList<>(), new ArrayList<>(), user);
        Cart cart1;

        List<CartDto> cartDtoList;
        List<Cart> cartList = new ArrayList<>();

        //WHEN
        cart1 = cartMapper.mapToCart(cartDto);
        cartDto1 = cartMapper.mapToCartDto(cart);

        cartList.add(cart);
        cartDtoList = cartMapper.mapToCartDtoList(cartList);

        //THEN
        Assert.assertEquals(cartDtoList.size(),cartList.size());

    }
}