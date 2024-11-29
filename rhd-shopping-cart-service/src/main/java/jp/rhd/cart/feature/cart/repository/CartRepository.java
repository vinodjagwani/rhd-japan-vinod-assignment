/**
 * Author: Vinod Jagwani
 */
package jp.rhd.cart.feature.cart.repository;

import jp.rhd.cart.feature.cart.repository.entity.Cart;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Mono;

public interface CartRepository extends ReactiveSortingRepository<Cart, String> {

    Mono<Cart> findByUserId(final String userId);

    Mono<Cart> findByUserIdAndSessionId(final String userId, final String sessionId);
}
