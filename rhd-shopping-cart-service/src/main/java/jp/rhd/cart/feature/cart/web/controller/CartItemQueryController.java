/**
 * Author: Vinod Jagwani
 */
package jp.rhd.cart.feature.cart.web.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jp.rhd.cart.annotation.DefaultApiResponse;
import jp.rhd.cart.feature.cart.repository.entity.CartItem;
import jp.rhd.cart.feature.cart.web.dto.request.CartItemQueryRequest;
import jp.rhd.cart.feature.cart.web.dto.response.CartItemCreateResponse;
import jp.rhd.cart.feature.cart.web.facade.CartItemQueryFacade;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/cart")
@Api(tags = "Cart", description = "Cart")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CartItemQueryController {

    CartItemQueryFacade cartItemQueryFacade;

    @DefaultApiResponse
    @PostMapping(value = "/list-cart", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Query Item Cart", nickname = "queryCartItems", notes = "This API is used for querying user items added in cart", response = CartItemCreateResponse.class)
    public ResponseEntity<Flux<CartItem>> queryCartItems(@Valid @RequestBody final CartItemQueryRequest request) {
        return new ResponseEntity<>(cartItemQueryFacade.queryCartItems(request), HttpStatus.OK);
    }
}
