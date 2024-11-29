/**
 * Author: Vinod Jagwani
 */
package jp.rhd.product.feature.item.web.controller;


import jp.rhd.product.feature.item.web.dto.create.ItemCreateRequest;
import jp.rhd.product.feature.item.web.dto.create.ItemCreateResponse;
import jp.rhd.product.feature.item.web.facade.ItemCreateFacade;
import jp.rhd.product.feature.item.web.facade.ItemQueryFacade;
import jp.rhd.product.feature.utils.MockUtils;
import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static java.util.Optional.ofNullable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@FieldDefaults(level = AccessLevel.PRIVATE)
@WebFluxTest(value = ItemController.class, excludeAutoConfiguration = {ReactiveSecurityAutoConfiguration.class})
class ItemControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    ItemQueryFacade itemQueryFacade;

    @MockBean
    ItemCreateFacade itemCreateFacade;

    @Test
    @SneakyThrows
    void testCreateItem() {
        final ItemCreateRequest request = ofNullable(MockUtils.getResource("mock/item-create-request.json", ItemCreateRequest.class)).orElse(new ItemCreateRequest());
        when(itemCreateFacade.createItem(any(ItemCreateRequest.class), any(Mono.class))).thenReturn(Mono.just(new ItemCreateResponse()));
        MultiValueMap<String, Object> multipartData = new LinkedMultiValueMap<>();
        multipartData.add("data", request);
        multipartData.add("file", new ByteArrayResource(new byte[]{1, 2, 3, 4}) {
            @Override
            public String getFilename() {
                return "image.png";
            }
        });
        webTestClient.post().uri("/v1/items")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .syncBody(multipartData)
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    @SneakyThrows
    void testQueryItems() {
        when(itemQueryFacade.queryItems(any(Pageable.class))).thenReturn(Mono.empty());
        webTestClient.get().uri("/v1/items")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    @SneakyThrows
    void testFilterItems() {
        when(itemQueryFacade.filterItems(any(String.class), any(Pageable.class))).thenReturn(Flux.empty());
        webTestClient.get().uri("/v1/items/filter?q=test")
                .exchange()
                .expectStatus().isOk();
    }
}
