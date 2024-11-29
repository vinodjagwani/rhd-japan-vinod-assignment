/**
 * Author: Vinod Jagwani
 */
package jp.rhd.product.feature.item.web.dto.create;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel(value = "ItemCreateResponse", description = "ItemCreateResponse contains response fields for creating item")
public class ItemCreateResponse {

    @ApiModelProperty(position = 1, name = "Item Id", example = "4742f94e-e8fd-46f7-8bb5-01fc36b13cf4", required = true)
    String itemId;

}
