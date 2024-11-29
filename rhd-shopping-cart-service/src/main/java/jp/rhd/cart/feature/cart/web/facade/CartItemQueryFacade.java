/**
 * Author: Vinod Jagwani
 */
package jp.rhd.cart.feature.cart.web.facade;

import jp.rhd.cart.feature.cart.repository.entity.CartItem;
import jp.rhd.cart.feature.cart.service.CartItemService;
import jp.rhd.cart.feature.cart.service.CartService;
import jp.rhd.cart.feature.cart.web.dto.request.CartItemQueryRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CartItemQueryFacade {

    CartService cartService;

    CartItemService cartItemService;

    public Flux<CartItem> queryCartItems(final CartItemQueryRequest request) {
        log.debug("Start querying items into cart with request [{}]", request);
        return cartService.findByUserIdAndSessionId(request.getUserId(), request.getSessionId()).flatMapMany(c -> cartItemService.findByCartId(c.getCartId()));
    }

}
