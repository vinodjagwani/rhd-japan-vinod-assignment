/**
 * Author: Vinod Jagwani
 */
package jp.rhd.order.feature.order.repository;

import jp.rhd.order.feature.order.repository.entity.Order;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

public interface OrderRepository extends ReactiveSortingRepository<Order, String> {

}
