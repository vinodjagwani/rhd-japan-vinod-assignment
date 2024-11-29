/**
 * Author: Vinod Jagwani
 */
package jp.rhd.cart.feature.cart.service.impl;

import jp.rhd.cart.feature.cart.repository.CartRepository;
import jp.rhd.cart.feature.cart.repository.entity.Cart;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class CartServiceImplTest {

    @Mock
    CartRepository cartRepository;

    @InjectMocks
    CartServiceImpl cartService;

    @Test
    void testCreateCart() {
        when(cartRepository.save(any(Cart.class))).thenReturn(Mono.just(new Cart()));
        cartService.createCart(new Cart());
        verify(cartRepository, atLeastOnce()).save(any(Cart.class));
    }

    @Test
    void testFindByUserId() {
        when(cartRepository.findByUserId(any(String.class))).thenReturn(Mono.just(new Cart()));
        cartService.findByUserId("123");
        verify(cartRepository, atLeastOnce()).findByUserId(any(String.class));
    }

    @Test
    void testFindByUserIdAndSessionId() {
        when(cartRepository.findByUserIdAndSessionId(any(String.class), any(String.class))).thenReturn(Mono.just(new Cart()));
        cartService.findByUserIdAndSessionId("123", "eyJhbGciOiJIUzUxMiJ9");
        verify(cartRepository, atLeastOnce()).findByUserIdAndSessionId(any(String.class), any(String.class));
    }
}
