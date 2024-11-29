/**
 * Author: Vinod Jagwani
 */
package jp.rhd.product.feature.item.repository;

import jp.rhd.product.feature.item.repository.entity.Item;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;

public interface ItemRepository extends ReactiveSortingRepository<Item, String> {

    Flux<Item> findByNameLikeIgnoringCase(final String q, final Pageable pageable);
}
