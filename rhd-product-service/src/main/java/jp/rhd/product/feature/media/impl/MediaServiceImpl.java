/**
 * Author: Vinod Jagwani
 */
package jp.rhd.product.feature.media.impl;

import jp.rhd.product.feature.media.MediaService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.ReactiveGridFsOperations;
import org.springframework.data.mongodb.gridfs.ReactiveGridFsResource;
import org.springframework.data.mongodb.gridfs.ReactiveGridFsTemplate;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.InputStream;


@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MediaServiceImpl implements MediaService {

    ReactiveGridFsTemplate reactiveGridFsTemplate;

    ReactiveGridFsOperations reactiveGridFsOperations;

    @Override
    public Mono<String> uploadMedia(final FilePart file) {
        log.debug("Start calling upload media into mongodb [{}]", file);
        return reactiveGridFsTemplate.store(file.content(), file.filename()).map(ObjectId::toHexString);
    }

    @Override
    public Mono<InputStream> downloadMedia(final String objectId) {
        log.debug("Start calling download media from mongodb [{}]", objectId);
        return reactiveGridFsTemplate.findOne(new Query(Criteria.where("_id").is(objectId))).map(reactiveGridFsOperations::getResource)
                .flatMap(reactiveGridFsResource -> reactiveGridFsResource.flatMap(ReactiveGridFsResource::getInputStream));
    }
}
