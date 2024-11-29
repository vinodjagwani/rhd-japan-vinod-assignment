/**
 * Author: Vinod Jagwani
 */
package jp.rhd.cart.feature.checkout.service.impl;

import jp.rhd.cart.feature.checkout.web.dto.OrderPostRequest;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class OrderPostServiceImplTest {

    RabbitTemplate rabbitTemplate;

    OrderPostServiceImpl orderPostService;

    @BeforeEach
    void init() {
        rabbitTemplate = Mockito.mock(RabbitTemplate.class);
        orderPostService = new OrderPostServiceImpl(rabbitTemplate, "test", "test");
    }

    @Test
    void testPostOrder() {
        doNothing().when(rabbitTemplate).convertAndSend(any(String.class), any(String.class), any(OrderPostRequest.class));
        orderPostService.postOrder(new OrderPostRequest());
        verify(rabbitTemplate, atLeastOnce()).convertAndSend(any(String.class), any(String.class), any(OrderPostRequest.class));
    }
}
