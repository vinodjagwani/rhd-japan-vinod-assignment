/**
 * Author: Vinod Jagwani
 */
package jp.rhd.product.feature.item.web.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import jp.rhd.product.annotation.DefaultApiResponse;
import jp.rhd.product.feature.item.repository.entity.PageSupport;
import jp.rhd.product.feature.item.web.dto.create.ItemCreateRequest;
import jp.rhd.product.feature.item.web.dto.create.ItemCreateResponse;
import jp.rhd.product.feature.item.web.dto.query.ItemQueryResponse;
import jp.rhd.product.feature.item.web.facade.ItemCreateFacade;
import jp.rhd.product.feature.item.web.facade.ItemQueryFacade;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/items")
@Api(tags = "Items", description = "Items")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ItemController {

    ItemQueryFacade itemQueryFacade;

    ItemCreateFacade itemCreateFacade;

    @DefaultApiResponse
    @ApiImplicitParam(name = "data", value = "json value", required = true, dataType = "ItemCreateRequest", paramType = "body")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create Item Api", nickname = "createItem", notes = "This API is used for creating item", response = ItemCreateResponse.class)
    public ResponseEntity<Mono<ItemCreateResponse>> createItem(@Valid @NotNull @RequestPart("data") final ItemCreateRequest request,
                                                               @RequestPart(value = "file", required = false) final Mono<FilePart> file) {
        return new ResponseEntity<>(itemCreateFacade.createItem(request, file), HttpStatus.CREATED);
    }

    @DefaultApiResponse
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List All Items", nickname = "queryItems", notes = "This API is used for list items", response = ItemQueryResponse.class)
    public ResponseEntity<Mono<PageSupport<ItemQueryResponse>>> queryItems(final @PageableDefault(value = 20) Pageable pageable) {
        return new ResponseEntity<>(itemQueryFacade.queryItems(pageable), HttpStatus.OK);
    }

    @DefaultApiResponse
    @GetMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Filter Item Api", nickname = "filterItems", notes = "This API is used for search items", response = ItemQueryResponse.class)
    public ResponseEntity<Flux<ItemQueryResponse>> filterItems(@RequestParam("q") final String q, final Pageable pageable) {
        return new ResponseEntity<>(itemQueryFacade.filterItems(q, pageable), HttpStatus.OK);
    }
}
