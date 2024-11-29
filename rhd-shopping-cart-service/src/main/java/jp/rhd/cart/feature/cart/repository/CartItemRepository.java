/**
 * Author: Vinod Jagwani
 */
package jp.rhd.cart.feature.cart.repository;

import jp.rhd.cart.feature.cart.repository.entity.CartItem;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;

public interface CartItemRepository extends ReactiveSortingRepository<CartItem, String> {

    Flux<CartItem> findByCartId(final String cartId);

}
