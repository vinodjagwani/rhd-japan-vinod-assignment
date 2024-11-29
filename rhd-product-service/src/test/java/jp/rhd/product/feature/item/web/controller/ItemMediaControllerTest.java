/**
 * Author: Vinod Jagwani
 */
package jp.rhd.product.feature.item.web.controller;


import jp.rhd.product.feature.media.MediaService;
import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.io.ByteArrayInputStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@FieldDefaults(level = AccessLevel.PRIVATE)
@WebFluxTest(value = ItemMediaController.class, excludeAutoConfiguration = {ReactiveSecurityAutoConfiguration.class})
class ItemMediaControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    MediaService mediaService;

    @Test
    @SneakyThrows
    void testGetItemMedia() {
        when(mediaService.downloadMedia(any(String.class))).thenReturn(Mono.just(new ByteArrayInputStream("test".getBytes())));
        webTestClient.get().uri("/v1/items/media/{mediaId}", "1234")
                .exchange()
                .expectStatus().isOk();
    }
}
