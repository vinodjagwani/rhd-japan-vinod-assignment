/**
 * Author: Vinod Jagwani
 */
package jp.rhd.cart.feature.cart.web.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel(value = "CartItemCreateResponse", description = "CartItemCreateResponse contains response fields for creating cart item")
public class CartItemCreateResponse {

    @ApiModelProperty(position = 1, name = "cartItemId", value = "96d6a466-7d56-4e1b-bf10-846bd9517a6d", required = true)
    String cartItemId;

    @ApiModelProperty(position = 2, name = "cartId", value = "96d6a466-7d56-4e1b-bf10-846bd9517a6d", required = true)
    String cartId;

    @ApiModelProperty(position = 3, name = "itemId", value = "96d6a466-7d56-4e1b-bf10-846bd9517a6d", required = true)
    String itemId;

    @ApiModelProperty(position = 4, name = "price", value = "23", required = true)
    BigDecimal price;

    @ApiModelProperty(position = 5, name = "quantity", value = "23", required = true)
    Integer quantity;

}
