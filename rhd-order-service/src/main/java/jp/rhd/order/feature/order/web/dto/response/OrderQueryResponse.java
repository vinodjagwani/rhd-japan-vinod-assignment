/**
 * Author: Vinod Jagwani
 */
package jp.rhd.order.feature.order.web.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(value = "OrderQueryResponse", description = "OrderQueryResponse contains response fields for query order")
public class OrderQueryResponse {

    @ApiModelProperty(position = 1, name = "orderId", example = "96d6a466-7d56-4e1b-bf10-846bd9517a6d", required = true)
    String orderId;

    @ApiModelProperty(position = 2, name = "userId", example = "96d6a466-7d56-4e1b-bf10-846bd9517a6d", required = true)
    String userId;

    @ApiModelProperty(position = 3, name = "firstName", example = "test", required = true)
    String firstName;

    @ApiModelProperty(position = 4, name = "lastName", example = "test", required = true)
    String lastName;

    @ApiModelProperty(position = 5, name = "totalAmount", example = "23", required = true)
    BigDecimal totalAmount;
}
