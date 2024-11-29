/**
 * Author: Vinod Jagwani
 */
package jp.rhd.order.feature.order.web.controller;


import jp.rhd.order.feature.order.utils.MockUtils;
import jp.rhd.order.feature.order.web.dto.response.OrderQueryResponse;
import jp.rhd.order.feature.order.web.facade.OrderQueryFacade;
import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import static java.util.Optional.ofNullable;
import static org.mockito.Mockito.when;

@FieldDefaults(level = AccessLevel.PRIVATE)
@WebFluxTest(value = OrderQueryController.class, excludeAutoConfiguration = {ReactiveSecurityAutoConfiguration.class})
class OrderQueryControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    OrderQueryFacade orderQueryFacade;

    @Test
    @SneakyThrows
    void testQueryOrders() {
        final OrderQueryResponse[] response = ofNullable(MockUtils.getResource("mock/query-orders.json", OrderQueryResponse[].class)).orElse(new OrderQueryResponse[]{});
        when(orderQueryFacade.queryAllOrders()).thenReturn(Flux.just(response));
        webTestClient.get().uri("/v1/orders")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.[0].orderId").isNotEmpty();

    }
}
