/**
 * Author: Vinod Jagwani
 */
package jp.rhd.cart.feature.cart.web.facade;

import jp.rhd.cart.feature.cart.repository.entity.Cart;
import jp.rhd.cart.feature.cart.service.CartItemService;
import jp.rhd.cart.feature.cart.service.CartService;
import jp.rhd.cart.feature.cart.web.dto.request.CartItemCreateRequest;
import jp.rhd.cart.feature.cart.web.dto.request.CartItemData;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class CartItemCreateFacadeTest {

    @Mock
    CartService cartService;

    @Mock
    CartItemService cartItemService;

    @InjectMocks
    CartItemCreateFacade cartItemCreateFacade;


    @Test
    void testAddItemIntoCart() {
        when(cartService.findByUserIdAndSessionId(any(String.class), any(String.class))).thenReturn(Mono.just(buildCart(buildCartItemCreateRequest())));
        when(cartService.createCart(any(Cart.class))).thenReturn(Mono.just(buildCart(buildCartItemCreateRequest())));
        cartItemCreateFacade.addItemIntoCart(buildCartItemCreateRequest());
        verify(cartService, atLeastOnce()).findByUserIdAndSessionId(any(String.class), any(String.class));
        verify(cartService, atLeastOnce()).createCart(any(Cart.class));
    }

    private CartItemCreateRequest buildCartItemCreateRequest() {
        final CartItemCreateRequest request = new CartItemCreateRequest();
        request.setSessionId("1234");
        request.setUserId("214");
        request.setFirstName("test");
        request.setLastName("test");
        final CartItemData cartItemData = new CartItemData();
        cartItemData.setItemId("234");
        cartItemData.setPrice(BigDecimal.ONE);
        cartItemData.setQuantity(1);
        request.setItems(List.of(cartItemData));
        return request;
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
}
