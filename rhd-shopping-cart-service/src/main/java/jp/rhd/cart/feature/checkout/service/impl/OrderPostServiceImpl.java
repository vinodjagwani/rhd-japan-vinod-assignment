/**
 * Author: Vinod Jagwani
 */
package jp.rhd.cart.feature.checkout.service.impl;

import jp.rhd.cart.feature.checkout.service.OrderPostService;
import jp.rhd.cart.feature.checkout.web.dto.OrderPostRequest;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class OrderPostServiceImpl implements OrderPostService {

    String queueName;

    String exchange;

    RabbitTemplate rabbitTemplate;

    public OrderPostServiceImpl(final RabbitTemplate rabbitTemplate, @Value("${spring.rabbitmq.routingkey}") final String queueName,
                                final @Value("${spring.rabbitmq.exchange}") String exchange) {
        this.queueName = queueName;
        this.exchange = exchange;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void postOrder(final OrderPostRequest request) {
        log.debug("Start sending order detail to rabbitmq with request [{}]", request);
        rabbitTemplate.convertAndSend(exchange, queueName, request);
    }
}
