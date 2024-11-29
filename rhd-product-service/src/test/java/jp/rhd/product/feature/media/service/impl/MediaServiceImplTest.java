/**
 * Author: Vinod Jagwani
 */
package jp.rhd.product.feature.media.service.impl;

import jp.rhd.product.feature.media.impl.MediaServiceImpl;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.reactivestreams.Publisher;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.ReactiveGridFsOperations;
import org.springframework.data.mongodb.gridfs.ReactiveGridFsTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.file.Path;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class MediaServiceImplTest {

    @Mock
    ReactiveGridFsTemplate reactiveGridFsTemplate;

    @Mock
    ReactiveGridFsOperations reactiveGridFsOperations;

    @InjectMocks
    MediaServiceImpl mediaService;

    @Test
    void testUploadMedia() {
        when(reactiveGridFsTemplate.store(any(Publisher.class), any(String.class))).thenReturn(Mono.empty());
        mediaService.uploadMedia(new FilePart() {
            @Override
            public String filename() {
                return "image.png";
            }

            @Override
            public Mono<Void> transferTo(final Path path) {
                return null;
            }

            @Override
            public String name() {
                return "image.png";
            }

            @Override
            public HttpHeaders headers() {
                return null;
            }

            @Override
            public Flux<DataBuffer> content() {
                return Flux.empty();
            }
        });
        verify(reactiveGridFsTemplate, atLeastOnce()).store(any(Publisher.class), any(String.class));
    }

    @Test
    void testDownloadMedia() {
        when(reactiveGridFsTemplate.findOne(any(Query.class))).thenReturn(Mono.empty());
        mediaService.downloadMedia("1234");
        verify(reactiveGridFsTemplate, atLeastOnce()).findOne(any(Query.class));
    }
}
