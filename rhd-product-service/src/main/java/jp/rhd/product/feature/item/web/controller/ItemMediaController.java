/**
 * Author: Vinod Jagwani
 */
package jp.rhd.product.feature.item.web.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jp.rhd.product.annotation.DefaultApiResponse;
import jp.rhd.product.feature.media.MediaService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/items/media")
@Api(tags = "Items", description = "Items")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ItemMediaController {

    MediaService mediaService;

    @DefaultApiResponse
    @GetMapping(value = "/{mediaId}", produces = MediaType.IMAGE_PNG_VALUE)
    @ApiOperation(value = "Download media", nickname = "getItemMedia", notes = "This API is used for download item image")
    public ResponseEntity<Mono<InputStreamResource>> getItemMedia(@PathVariable("mediaId") final String mediaId) {
        return new ResponseEntity<>(mediaService.downloadMedia(mediaId).map(InputStreamResource::new), HttpStatus.OK);
    }
}
