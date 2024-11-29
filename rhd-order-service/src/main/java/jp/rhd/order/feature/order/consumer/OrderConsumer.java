/**
 * Author: Vinod Jagwani
 */
package jp.rhd.order.feature.order.consumer;


import jp.rhd.order.feature.order.repository.entity.Order;
import jp.rhd.order.feature.order.service.OrderService;
import jp.rhd.order.feature.order.web.dto.request.OrderPostRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class OrderConsumer {

    OrderService orderService;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receivedOrder(final OrderPostRequest data) {
        log.debug("Order arrived successfully with data [{}]", data);
        final Order savedOrder = orderService.createOrder(buildOrder(data)).block();
        log.debug("Order saved successfully with orderId [{}]", savedOrder.getOrderId());
    }

    private Order buildOrder(final OrderPostRequest request) {
        final Order order = new Order();
        order.setOrderId(UUID.randomUUID().toString());
        order.setUserId(request.getUserId());
        order.setTotalAmount(request.getTotalAmount());
        order.setFirstName(request.getFirstName());
        order.setLastName(request.getLastName());
        return order;
    }
}
