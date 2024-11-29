/**
 * Author: Vinod Jagwani
 */
package jp.rhd.cart.feature.cart.web.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jp.rhd.cart.annotation.DefaultApiResponse;
import jp.rhd.cart.feature.cart.web.dto.request.CartItemCreateRequest;
import jp.rhd.cart.feature.cart.web.dto.response.CartItemCreateResponse;
import jp.rhd.cart.feature.cart.web.facade.CartItemCreateFacade;
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
import javax.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/cart")
@Api(tags = "Cart", description = "Cart")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CartItemCreateController {

    CartItemCreateFacade cartItemCreateFacade;

    @DefaultApiResponse
    @PostMapping(value = "/add-cart", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create Item Cart", nickname = "addItemsInCart", notes = "This API is used for creating item cart", response = CartItemCreateResponse.class)
    public ResponseEntity<Flux<CartItemCreateResponse>> addItemsInCart(@Valid @NotNull @RequestBody final CartItemCreateRequest request) {
        return new ResponseEntity<>(cartItemCreateFacade.addItemIntoCart(request), HttpStatus.CREATED);
    }

}
