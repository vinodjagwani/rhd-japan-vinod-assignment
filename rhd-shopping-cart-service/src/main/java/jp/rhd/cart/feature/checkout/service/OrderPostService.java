/**
 * Author: Vinod Jagwani
 */
package jp.rhd.cart.feature.checkout.service;

import jp.rhd.cart.feature.checkout.web.dto.OrderPostRequest;

public interface OrderPostService {

    void postOrder(final OrderPostRequest request);
}
