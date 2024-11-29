/**
 * Author: Vinod Jagwani
 */
package jp.rhd.cart.feature.cart.web.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel(value = "CartItemCreateRequest", description = "CartItemCreateRequest contains request fields for creating cart item")
public class CartItemCreateRequest {


    @NotEmpty(message = "userId can't be null or empty")
    @ApiModelProperty(position = 1, name = "userId", value = "96d6a466-7d56-4e1b-bf10-846bd9517a6d", required = true)
    String userId;

    @NotEmpty(message = "sessionId can't be null or empty")
    @ApiModelProperty(position = 2, name = "sessionId", value = "96d6a466-7d56-4e1b-bf10-846bd9517a6d", required = true)
    String sessionId;

    @NotEmpty(message = "firstName can't be null or empty")
    @ApiModelProperty(position = 3, name = "firstName", value = "test", required = true)
    String firstName;

    @NotEmpty(message = "lastName can't be null or empty")
    @ApiModelProperty(position = 4, name = "lastName", value = "test", required = true)
    String lastName;

    @NotEmpty(message = "items can't be null or empty")
    @ApiModelProperty(position = 5, name = "items", required = true)
    List<@NotNull(message = "item can't be null or empty") @Valid CartItemData> items;

}
