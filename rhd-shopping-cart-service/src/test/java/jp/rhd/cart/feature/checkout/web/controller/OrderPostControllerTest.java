/**
 * Author: Vinod Jagwani
 */
package jp.rhd.cart.feature.checkout.web.controller;


import jp.rhd.cart.feature.cart.utils.MockUtils;
import jp.rhd.cart.feature.checkout.service.OrderPostService;
import jp.rhd.cart.feature.checkout.web.dto.OrderPostRequest;
import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static java.util.Optional.ofNullable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

@FieldDefaults(level = AccessLevel.PRIVATE)
@WebFluxTest(value = OrderPostController.class, excludeAutoConfiguration = {ReactiveSecurityAutoConfiguration.class})
class OrderPostControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    OrderPostService orderPostService;

    @Test
    @SneakyThrows
    void testCheckout() {
        final String request = ofNullable(MockUtils.getResource("mock/checkout-request.json", String.class)).orElse("");
        doNothing().when(orderPostService).postOrder(any(OrderPostRequest.class));
        webTestClient.post().uri("/v1/checkout")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(request))
                .exchange()
                .expectStatus().isCreated();
    }

}
