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
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel(value = "CartItemData", description = "CartItemData contains data fields for creating cart item")
public class CartItemData {

    @NotEmpty(message = "itemId can't be null or empty")
    @ApiModelProperty(position = 1, name = "itemId", example = "96d6a466-7d56-4e1b-bf10-846bd9517a6d", required = true)
    String itemId;

    @NotNull(message = "price can't be null or empty")
    @ApiModelProperty(position = 2, name = "price", example = "12", required = true)
    BigDecimal price;

    @NotNull(message = "quantity can't be null or empty")
    @ApiModelProperty(position = 3, name = "quantity", example = "213", required = true)
    Integer quantity;

}
