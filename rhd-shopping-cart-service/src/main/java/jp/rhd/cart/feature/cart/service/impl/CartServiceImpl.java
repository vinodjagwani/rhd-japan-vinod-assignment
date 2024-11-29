/**
 * Author: Vinod Jagwani
 */
package jp.rhd.cart.feature.cart.service.impl;

import jp.rhd.cart.feature.cart.repository.CartRepository;
import jp.rhd.cart.feature.cart.repository.entity.Cart;
import jp.rhd.cart.feature.cart.service.CartService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CartServiceImpl implements CartService {

    CartRepository cartRepository;

    @Override
    public Mono<Cart> createCart(final Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Mono<Cart> findByUserId(final String userId) {
        return cartRepository.findByUserId(userId);
    }

    @Override
    public Mono<Cart> findByUserIdAndSessionId(final String userId, String sessionId) {
        return cartRepository.findByUserIdAndSessionId(userId, sessionId);
    }

}
