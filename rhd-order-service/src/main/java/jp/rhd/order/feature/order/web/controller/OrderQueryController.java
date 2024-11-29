/**
 * Author: Vinod Jagwani
 */
package jp.rhd.order.feature.order.web.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jp.rhd.order.annotation.DefaultApiResponse;
import jp.rhd.order.feature.order.web.dto.response.OrderQueryResponse;
import jp.rhd.order.feature.order.web.facade.OrderQueryFacade;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/orders")
@Api(tags = "Order", description = "Order")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class OrderQueryController {

    OrderQueryFacade orderQueryFacade;

    @DefaultApiResponse
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Query Orders", nickname = "queryOrders", notes = "This API is used for querying orders")
    public ResponseEntity<Flux<OrderQueryResponse>> queryOrders() {
        return new ResponseEntity<>(orderQueryFacade.queryAllOrders(), HttpStatus.OK);
    }
}
