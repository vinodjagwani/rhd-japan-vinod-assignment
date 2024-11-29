/**
 * Author: Vinod Jagwani
 */
package jp.rhd.cart.feature.cart.service;


import jp.rhd.cart.feature.cart.repository.entity.CartItem;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CartItemService {

    Flux<CartItem> findByCartId(final String cartId);

    Mono<CartItem> createCartItem(final CartItem cartItem);

    Flux<CartItem> createCartItems(final List<CartItem> cartItem);

}
