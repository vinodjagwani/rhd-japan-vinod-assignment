/**
 * Author: Vinod Jagwani
 */
package jp.rhd.order.feature.order.web.facade;

import jp.rhd.order.feature.order.repository.entity.Order;
import jp.rhd.order.feature.order.service.OrderService;
import jp.rhd.order.feature.order.web.dto.response.OrderQueryResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class OrderQueryFacade {

    OrderService orderService;

    public Flux<OrderQueryResponse> queryAllOrders() {
        log.debug("Start calling querying orders all");
        return orderService.findAll().map(this::buildOrderQueryResponse);
    }

    private OrderQueryResponse buildOrderQueryResponse(final Order order) {
        final OrderQueryResponse response = new OrderQueryResponse();
        response.setOrderId(order.getOrderId());
        response.setFirstName(order.getFirstName());
        response.setLastName(order.getLastName());
        response.setTotalAmount(order.getTotalAmount());
        response.setUserId(order.getUserId());
        return response;
    }
}
