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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CartMapperTestSuite {

    @InjectMocks
    private CartMapper cartMapper;
    @Mock
    private CarMapper carMapper;
    @Mock
    private OrderMapper orderMapper;
    @Mock
    private UserMapper userMapper;

    @Test
    public void testCartMapper() {
        //GIVEN
        UserDto userDto = new UserDto();
        CartDto cartDto = new CartDto(1L, new BigDecimal(0), new ArrayList<>(), new ArrayList<>());
        CartDto cartDto1;

        User user = new User();
        Cart cart = new Cart(1L, new BigDecimal(0), new ArrayList<>(), new ArrayList<>());
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
        assertThat(cart).isEqualToComparingFieldByField(cart1);
        assertThat(cartDto).isEqualToComparingFieldByField(cartDto1);

    }
}