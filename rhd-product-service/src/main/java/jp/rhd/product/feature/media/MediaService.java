/**
 * Author: Vinod Jagwani
 */
package jp.rhd.product.feature.media;

import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

import java.io.InputStream;

public interface MediaService {

    Mono<String> uploadMedia(final FilePart file);

    Mono<InputStream> downloadMedia(final String objectId);
}
