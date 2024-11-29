/**
 * Author: Vinod Jagwani
 */
package jp.rhd.product.feature.item.web.facade;


import jp.rhd.product.feature.item.repository.entity.Item;
import jp.rhd.product.feature.item.service.ItemService;
import jp.rhd.product.feature.item.web.dto.create.ItemCreateRequest;
import jp.rhd.product.feature.item.web.dto.create.ItemCreateResponse;
import jp.rhd.product.feature.media.MediaService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ItemCreateFacade {

    ModelMapper modelMapper;

    ItemService itemService;

    MediaService mediaUploadService;

    public Mono<ItemCreateResponse> createItem(final ItemCreateRequest request, final Mono<FilePart> file) {
        log.debug("Start calling creating item service with request [{}]", request);
        return file.flatMap(mediaUploadService::uploadMedia)
                .flatMap(id -> itemService.createItem(buildItem(request, id)).map(r -> modelMapper.map(r, ItemCreateResponse.class)))
                .switchIfEmpty(itemService.createItem(buildItem(request, null)).map(r -> modelMapper.map(r, ItemCreateResponse.class)));
    }

    private Item buildItem(final ItemCreateRequest request, final String imageId) {
        final Item item = new Item();
        item.setItemId(UUID.randomUUID().toString());
        item.setName(request.getName());
        item.setImageId(imageId);
        item.setDescription(request.getDescription());
        item.setTotalPrice(request.getItemAmount());
        return item;
    }
}
