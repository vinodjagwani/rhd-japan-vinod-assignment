/**
 * Author: Vinod Jagwani
 */
package jp.rhd.product.feature.item.web.dto.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel(value = "ItemQueryResponse", description = "ItemQueryResponse contains response fields for querying item")
public class ItemQueryResponse {

    @ApiModelProperty(position = 1, name = "itemId", example = "09983c1f-fd8a-4f38-9844-8e8b4e20e5bb", required = true)
    String itemId;

    @ApiModelProperty(position = 2, name = "name", example = "test", required = true)
    String name;

    @ApiModelProperty(position = 3, name = "description", example = "test", required = true)
    String description;

    @ApiModelProperty(position = 4, name = "itemPrice", example = "345", required = true)
    BigDecimal itemPrice;

    @ApiModelProperty(position = 5, name = "imageId", example = "09983c1", required = true)
    String imageId;
}
