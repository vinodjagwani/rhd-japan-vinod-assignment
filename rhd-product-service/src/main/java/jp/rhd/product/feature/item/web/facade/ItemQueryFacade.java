/**
 * Author: Vinod Jagwani
 */
package jp.rhd.product.feature.item.web.facade;


import jp.rhd.product.feature.item.repository.entity.Item;
import jp.rhd.product.feature.item.repository.entity.PageSupport;
import jp.rhd.product.feature.item.service.ItemService;
import jp.rhd.product.feature.item.web.dto.query.ItemQueryResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ItemQueryFacade {

    ItemService itemService;

    public Mono<PageSupport<ItemQueryResponse>> queryItems(final Pageable pageable) {
        log.debug("Start calling querying item service with pageAble request [{}]", pageable);
        return itemService.queryItems(pageable)
                .map(data -> new PageSupport<>(data.getContent().stream()
                        .map(this::buildItemQueryResponse)
                        .collect(Collectors.toList()), pageable.getPageNumber(), pageable.getPageSize(), data.getContent().size()));
    }

    public Flux<ItemQueryResponse> filterItems(final String q, final Pageable pageable) {
        log.debug("Start calling filter item service with filter [{}] and pageAble request [{}]", q, pageable);
        return itemService.filterItems(q, pageable).map(this::buildItemQueryResponse);
    }

    private ItemQueryResponse buildItemQueryResponse(final Item item) {
        final ItemQueryResponse response = new ItemQueryResponse();
        response.setItemId(item.getItemId());
        response.setName(item.getName());
        response.setDescription(item.getDescription());
        response.setItemPrice(item.getTotalPrice());
        response.setImageId(item.getImageId());
        return response;
    }

}
