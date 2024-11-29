/**
 * Author: Vinod Jagwani
 */
package jp.rhd.cart.feature.cart.web.facade;

import com.google.common.collect.Lists;
import jp.rhd.cart.feature.cart.repository.entity.Cart;
import jp.rhd.cart.feature.cart.repository.entity.CartItem;
import jp.rhd.cart.feature.cart.service.CartItemService;
import jp.rhd.cart.feature.cart.service.CartService;
import jp.rhd.cart.feature.cart.web.dto.request.CartItemCreateRequest;
import jp.rhd.cart.feature.cart.web.dto.response.CartItemCreateResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CartItemCreateFacade {

    CartService cartService;

    CartItemService cartItemService;

    public Flux<CartItemCreateResponse> addItemIntoCart(final CartItemCreateRequest request) {
        log.debug("Start adding items into cart with request [{}]", request);
        return cartService.findByUserIdAndSessionId(request.getUserId(), request.getSessionId())
                .flatMapMany(c -> cartItemService.createCartItems(buildCartItems(c.getCartId(), request)).map(this::buildCartItemCreateResponse))
                .switchIfEmpty(saveCartAndItems(request).map(this::buildCartItemCreateResponse));
    }

    private Flux<CartItem> saveCartAndItems(final CartItemCreateRequest request) {
        return cartService.createCart(buildCart(request)).flatMapMany(c -> cartItemService.createCartItems(buildCartItems(c.getCartId(), request)));
    }

    private List<CartItem> buildCartItems(final String cartId, final CartItemCreateRequest request) {
        final List<CartItem> cartItems = Lists.newArrayList();
        ListUtils.emptyIfNull(request.getItems()).forEach(cartItemData -> {
            final CartItem cartItem = new CartItem();
            cartItem.setCartItemId(UUID.randomUUID().toString());
            cartItem.setCartId(cartId);
            cartItem.setItemId(cartItemData.getItemId());
            cartItem.setPrice(cartItemData.getPrice());
            cartItem.setQuantity(cartItemData.getQuantity());
            cartItems.add(cartItem);
        });
        return cartItems;
    }

    private Cart buildCart(final CartItemCreateRequest request) {
        final Cart cart = new Cart();
        cart.setCartId(UUID.randomUUID().toString());
        cart.setFirstName(request.getFirstName());
        cart.setLastName(request.getLastName());
        cart.setSessionId(request.getSessionId());
        cart.setUserId(request.getUserId());
        return cart;
    }

    private CartItemCreateResponse buildCartItemCreateResponse(final CartItem cartItem) {
        final CartItemCreateResponse response = new CartItemCreateResponse();
        response.setCartId(cartItem.getCartId());
        response.setCartItemId(cartItem.getCartItemId());
        response.setItemId(cartItem.getItemId());
        response.setPrice(cartItem.getPrice());
        response.setQuantity(cartItem.getQuantity());
        return response;
    }
}
