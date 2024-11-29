/**
 * Author: Vinod Jagwani
 */
package jp.rhd.cart.feature.cart.service.impl;


import jp.rhd.cart.feature.cart.repository.CartItemRepository;
import jp.rhd.cart.feature.cart.repository.entity.CartItem;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class CartItemServiceImplTest {

    @Mock
    CartItemRepository cartItemRepository;

    @InjectMocks
    CartItemServiceImpl cartItemService;

    @Test
    void testFindByCartId() {
        when(cartItemRepository.findByCartId(any(String.class))).thenReturn(Flux.just(new CartItem()));
        cartItemService.findByCartId("13231");
        verify(cartItemRepository, atLeastOnce()).findByCartId(any(String.class));
    }

    @Test
    void createCartItem() {
        when(cartItemRepository.save(any(CartItem.class))).thenReturn(Mono.just(new CartItem()));
        cartItemService.createCartItem(new CartItem());
        verify(cartItemRepository, atLeastOnce()).save(any(CartItem.class));
    }

    @Test
    void createCartItems() {
        when(cartItemRepository.saveAll(anyList())).thenReturn(Flux.just(new CartItem()));
        cartItemService.createCartItems(List.of(new CartItem()));
        verify(cartItemRepository, atLeastOnce()).saveAll(anyList());
    }
}
