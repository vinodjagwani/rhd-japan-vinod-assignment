/**
 * Author: Vinod Jagwani
 */
package jp.rhd.cart.feature.cart.web.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel(value = "CartItemQueryRequest", description = "CartItemQueryRequest contains request fields for querying cart items")
public class CartItemQueryRequest {

    @NotEmpty(message = "userId can't be null or empty")
    @ApiModelProperty(position = 1, name = "sessionId", value = "96d6a466-7d56-4e1b-bf10-846bd9517a6d", required = true)
    String userId;

    @NotEmpty(message = "sessionId can't be null or empty")
    @ApiModelProperty(position = 2, name = "sessionId", value = "96d6a466-7d56-4e1b-bf10-846bd9517a6d", required = true)
    String sessionId;
}
