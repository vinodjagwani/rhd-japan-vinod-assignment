/**
 * Author: Vinod Jagwani
 */
package jp.rhd.product.feature.item.service;

import jp.rhd.product.feature.item.repository.entity.Item;
import jp.rhd.product.feature.item.repository.entity.PageSupport;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ItemService {

    Mono<Item> createItem(final Item item);

    Mono<PageSupport<Item>> queryItems(final Pageable pageable);

    Flux<Item> filterItems(final String q, final Pageable pageable);
}
