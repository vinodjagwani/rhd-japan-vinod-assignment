/**
 * Author: Vinod Jagwani
 */
package jp.rhd.cart.feature.cart.web.controller;


import jp.rhd.cart.feature.cart.utils.MockUtils;
import jp.rhd.cart.feature.cart.web.dto.request.CartItemCreateRequest;
import jp.rhd.cart.feature.cart.web.dto.response.CartItemCreateResponse;
import jp.rhd.cart.feature.cart.web.facade.CartItemCreateFacade;
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
@WebFluxTest(value = CartItemCreateController.class, excludeAutoConfiguration = {ReactiveSecurityAutoConfiguration.class})
class CartItemCreateControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    CartItemCreateFacade cartItemCreateFacade;

    @Test
    @SneakyThrows
    void testAddItemsInCart() {
        final String response = ofNullable(MockUtils.getResource("mock/add-items-cart-request.json", String.class)).orElse("");
        when(cartItemCreateFacade.addItemIntoCart(any(CartItemCreateRequest.class))).thenReturn(Flux.just(new CartItemCreateResponse()));
        webTestClient.post().uri("/v1/cart/add-cart")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(response))
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.[0].cartItemId").isEmpty();
    }

}
