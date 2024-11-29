/**
 * Author: Vinod Jagwani
 */
package jp.rhd.cart.feature.cart.web.controller;


import jp.rhd.cart.feature.cart.repository.entity.CartItem;
import jp.rhd.cart.feature.cart.utils.MockUtils;
import jp.rhd.cart.feature.cart.web.dto.request.CartItemQueryRequest;
import jp.rhd.cart.feature.cart.web.facade.CartItemQueryFacade;
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
import reactor.core.publisher.Flux;

import static java.util.Optional.ofNullable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@FieldDefaults(level = AccessLevel.PRIVATE)
@WebFluxTest(value = CartItemQueryController.class, excludeAutoConfiguration = {ReactiveSecurityAutoConfiguration.class})
class CartItemQueryControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    CartItemQueryFacade cartItemQueryFacade;

    @Test
    @SneakyThrows
    void testQueryCartItems() {
        final String response = ofNullable(MockUtils.getResource("mock/list-items-cart-request.json", String.class)).orElse("");
        when(cartItemQueryFacade.queryCartItems(any(CartItemQueryRequest.class))).thenReturn(Flux.just(new CartItem()));
        webTestClient.post().uri("/v1/cart/list-cart")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(response))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.[0].cartItemId").isEmpty();

    }
}
