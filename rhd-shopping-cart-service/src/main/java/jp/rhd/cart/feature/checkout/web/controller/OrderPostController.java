/**
 * Author: Vinod Jagwani
 */
package jp.rhd.cart.feature.checkout.web.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jp.rhd.cart.annotation.DefaultApiResponse;
import jp.rhd.cart.feature.checkout.service.OrderPostService;
import jp.rhd.cart.feature.checkout.web.dto.OrderPostRequest;
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
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/checkout")
@Api(tags = "Checkout", description = "Checkout")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class OrderPostController {

    OrderPostService orderPostService;

    @DefaultApiResponse
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Checkout", nickname = "postOrder", notes = "This API is used for checkout")
    public ResponseEntity<Mono<Void>> checkout(@Valid @RequestBody final OrderPostRequest request) {
        orderPostService.postOrder(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
