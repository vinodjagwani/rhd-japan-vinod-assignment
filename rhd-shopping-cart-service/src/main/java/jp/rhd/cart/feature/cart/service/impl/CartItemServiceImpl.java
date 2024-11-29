/**
 * Author: Vinod Jagwani
 */
package jp.rhd.cart.feature.cart.service.impl;


import jp.rhd.cart.feature.cart.repository.CartItemRepository;
import jp.rhd.cart.feature.cart.repository.entity.CartItem;
import jp.rhd.cart.feature.cart.service.CartItemService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CartItemServiceImpl implements CartItemService {

    CartItemRepository cartItemRepository;

    @Override
    public Flux<CartItem> findByCartId(final String cartId) {
        return cartItemRepository.findByCartId(cartId);
    }

    @Override
    public Mono<CartItem> createCartItem(final CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Override
    public Flux<CartItem> createCartItems(final List<CartItem> cartItem) {
        return cartItemRepository.saveAll(cartItem);
    }
}
