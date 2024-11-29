/**
 * Author: Vinod Jagwani
 */
package jp.rhd.product.feature.item.service.impl;

import jp.rhd.product.feature.item.repository.ItemRepository;
import jp.rhd.product.feature.item.repository.entity.Item;
import jp.rhd.product.feature.item.repository.entity.PageSupport;
import jp.rhd.product.feature.item.service.ItemService;
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
public class ItemServiceImpl implements ItemService {

    ItemRepository itemRepository;

    @Override
    public Mono<Item> createItem(final Item item) {
        log.info("Start creating item with itemId [{}]", item.getItemId());
        final Mono<Item> savedItem = itemRepository.save(item);
        log.info("Finish creating item with item [{}]", savedItem);
        return savedItem;
    }

    @Override
    public Mono<PageSupport<Item>> queryItems(final Pageable pageable) {
        log.debug("Start calling findAll items with pageAble request [{}]", pageable);
        return itemRepository.findAll().collectList().map(list -> new PageSupport<>(list
                .stream().skip(pageable.getPageNumber() * pageable.getPageSize())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList()), 0, 0, list.size()));

    }

    @Override
    public Flux<Item> filterItems(final String q, final Pageable pageable) {
        log.debug("Start calling findByNameLikeIgnoringCase items with pageAble request [{}]", pageable);
        return itemRepository.findByNameLikeIgnoringCase(q, pageable);
    }

}
