/**
 * Author: Vinod Jagwani
 */
package jp.rhd.order.feature.order.service.impl;

import jp.rhd.order.feature.order.repository.OrderRepository;
import jp.rhd.order.feature.order.repository.entity.Order;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class OrderServiceImplTest {

    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    OrderServiceImpl orderService;

    @Test
    void testFindAll() {
        when(orderRepository.findAll()).thenReturn(Flux.just(new Order()));
        orderService.findAll();
        verify(orderRepository, atLeastOnce()).findAll();
    }

    @Test
    void testCreateOrder() {
        when(orderRepository.save(any(Order.class))).thenReturn(Mono.just(new Order()));
        orderService.createOrder(new Order());
        verify(orderRepository, atLeastOnce()).save(any(Order.class));
    }
}
