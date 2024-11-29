/**
 * Author: Vinod Jagwani
 */
package jp.rhd.order.feature.order.web.dto.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel(value = "OrderPostRequest", description = "OrderPostRequest contains request fields for posting order")
public class OrderPostRequest {

    @NotEmpty(message = "userId can't be null or empty")
    @ApiModelProperty(position = 1, name = "userId", example = "96d6a466-7d56-4e1b-bf10-846bd9517a6d", required = true)
    String userId;

    @NotEmpty(message = "sessionId can't be null or empty")
    @ApiModelProperty(position = 2, name = "sessionId", example = "96d6a466-7d56-4e1b-bf10-846bd9517a6d", required = true)
    String sessionId;

    @NotEmpty(message = "firstName can't be null or empty")
    @ApiModelProperty(position = 3, name = "firstName", example = "test", required = true)
    String firstName;

    @NotEmpty(message = "lastName can't be null or empty")
    @ApiModelProperty(position = 4, name = "lastName", example = "test", required = true)
    String lastName;

    @NotEmpty(message = "status can't be null or empty")
    @ApiModelProperty(position = 5, name = "status", example = "PENDING", required = true)
    String status;

    @Email(message = "email is invalid")
    @NotEmpty(message = "email can't be null or empty")
    @ApiModelProperty(position = 6, name = "email", example = "test@gmail.com", required = true)
    String email;

    @NotNull(message = "totalAmount can't be null or empty")
    @ApiModelProperty(position = 7, name = "totalAmount", example = "23", required = true)
    BigDecimal totalAmount;

    @NotEmpty(message = "shippingAddress can't be null or empty")
    @ApiModelProperty(position = 8, name = "shippingAddress", example = "MA", required = true)
    String shippingAddress;

}
