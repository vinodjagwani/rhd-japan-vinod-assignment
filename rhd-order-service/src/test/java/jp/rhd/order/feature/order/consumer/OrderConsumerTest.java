/**
 * Author: Vinod Jagwani
 */
package jp.rhd.order.feature.order.consumer;


import jp.rhd.order.feature.order.repository.entity.Order;
import jp.rhd.order.feature.order.service.OrderService;
import jp.rhd.order.feature.order.web.dto.request.OrderPostRequest;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class OrderConsumerTest {

    @Mock
    OrderService orderService;

    @InjectMocks
    OrderConsumer orderConsumer;

    @Test
    void testReceivedOrder() {
        when(orderService.createOrder(any(Order.class))).thenReturn(Mono.just(new Order()));
        orderConsumer.receivedOrder(new OrderPostRequest());
        verify(orderService, atLeastOnce()).createOrder(any(Order.class));
    }
}
