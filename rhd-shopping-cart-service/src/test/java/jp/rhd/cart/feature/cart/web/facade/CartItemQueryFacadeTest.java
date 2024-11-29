/**
 * Author: Vinod Jagwani
 */
package jp.rhd.cart.feature.cart.web.facade;

import jp.rhd.cart.feature.cart.repository.entity.Cart;
import jp.rhd.cart.feature.cart.service.CartItemService;
import jp.rhd.cart.feature.cart.service.CartService;
import jp.rhd.cart.feature.cart.web.dto.request.CartItemQueryRequest;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class CartItemQueryFacadeTest {

    @Mock
    CartService cartService;

    @Mock
    CartItemService cartItemService;

    @InjectMocks
    CartItemQueryFacade cartItemQueryFacade;

    @Test
    void testQueryCartItems() {
        when(cartService.findByUserIdAndSessionId(any(String.class), any(String.class))).thenReturn(Mono.just(new Cart()));
        cartItemQueryFacade.queryCartItems(buildCartItemQueryRequest());
        verify(cartService, atLeastOnce()).findByUserIdAndSessionId(any(String.class), any(String.class));
    }

    private CartItemQueryRequest buildCartItemQueryRequest() {
        final CartItemQueryRequest request = new CartItemQueryRequest();
        request.setSessionId("1234");
        request.setUserId("214");
        return request;
    }

}
