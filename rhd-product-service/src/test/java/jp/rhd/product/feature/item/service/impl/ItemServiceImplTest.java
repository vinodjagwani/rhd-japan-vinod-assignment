/**
 * Author: Vinod Jagwani
 */
package jp.rhd.product.feature.item.service.impl;

import jp.rhd.product.feature.item.repository.ItemRepository;
import jp.rhd.product.feature.item.repository.entity.Item;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class ItemServiceImplTest {

    @Mock
    ItemRepository itemRepository;

    @InjectMocks
    ItemServiceImpl itemService;

    @Test
    void testCreateItem() {
        when(itemRepository.save(any(Item.class))).thenReturn(Mono.just(new Item()));
        itemService.createItem(new Item());
        verify(itemRepository, atLeastOnce()).save(any(Item.class));
    }

    @Test
    void testQueryItems() {
        when(itemRepository.findAll()).thenReturn(Flux.just(new Item()));
        itemService.queryItems(PageRequest.of(1, 1));
        verify(itemRepository, atLeastOnce()).findAll();
    }

    @Test
    void testFilterItems() {
        when(itemRepository.findByNameLikeIgnoringCase(any(String.class), any(Pageable.class))).thenReturn(Flux.just(new Item()));
        itemService.filterItems("test", PageRequest.of(1, 1));
        verify(itemRepository, atLeastOnce()).findByNameLikeIgnoringCase(any(String.class), any(Pageable.class));
    }

}
