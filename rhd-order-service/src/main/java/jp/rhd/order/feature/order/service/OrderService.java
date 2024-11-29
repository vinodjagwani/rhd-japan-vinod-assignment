/**
 * Author: Vinod Jagwani
 */
package jp.rhd.order.feature.order.service;

import jp.rhd.order.feature.order.repository.entity.Order;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderService {

    Flux<Order> findAll();

    Mono<Order> createOrder(final Order cart);


}
