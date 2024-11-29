/**
 * Author: Vinod Jagwani
 */
package jp.rhd.product.feature.item.web.dto.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel(value = "ItemQueryRequest", description = "ItemQueryRequest contains request fields for querying item")
public class ItemQueryRequest {

    @NotEmpty(message = "name can't be null or empty")
    @ApiModelProperty(position = 1, name = "name", example = "test", required = true)
    String name;
}
