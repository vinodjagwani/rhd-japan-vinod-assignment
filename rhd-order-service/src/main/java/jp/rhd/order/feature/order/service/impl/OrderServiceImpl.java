/**
 * Author: Vinod Jagwani
 */
package jp.rhd.order.feature.order.service.impl;

import jp.rhd.order.feature.order.repository.OrderRepository;
import jp.rhd.order.feature.order.repository.entity.Order;
import jp.rhd.order.feature.order.service.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class OrderServiceImpl implements OrderService {

    OrderRepository orderRepository;

    @Override
    public Flux<Order> findAll() {
        log.debug("Start querying all orders");
        return orderRepository.findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Mono<Order> createOrder(final Order order) {
        log.debug("Start creating order with orderId [{}]", order.getOrderId());
        return orderRepository.save(order).doOnSuccess(savedOrder -> log.debug("Finish creating order with orderId [{}]", savedOrder.getOrderId()));
    }
}
