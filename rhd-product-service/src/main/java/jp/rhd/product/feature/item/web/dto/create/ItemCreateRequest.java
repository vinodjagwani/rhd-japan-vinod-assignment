/**
 * Author: Vinod Jagwani
 */
package jp.rhd.product.feature.item.web.dto.create;


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
@ApiModel(value = "ItemCreateRequest", description = "ItemCreateRequest contains request fields for creating item")
public class ItemCreateRequest {

    @NotEmpty(message = "name can't be null or empty")
    @ApiModelProperty(position = 1, name = "name", example = "test", required = true)
    String name;

    @NotEmpty(message = "description can't be null or empty")
    @ApiModelProperty(position = 2, name = "description", example = "test", required = true)
    String description;

    @NotNull(message = "itemAmount can't be null or empty")
    @ApiModelProperty(position = 3, name = "itemAmount", example = "23", required = true)
    BigDecimal itemAmount;


}
