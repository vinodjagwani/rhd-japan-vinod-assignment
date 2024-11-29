/**
 * Author: Vinod Jagwani
 */
package jp.rhd.cart.feature.cart.service;

import jp.rhd.cart.feature.cart.repository.entity.Cart;
import reactor.core.publisher.Mono;

public interface CartService {

    Mono<Cart> createCart(final Cart cart);

    Mono<Cart> findByUserId(final String userId);

    Mono<Cart> findByUserIdAndSessionId(final String userId, final String sessionId);
}
