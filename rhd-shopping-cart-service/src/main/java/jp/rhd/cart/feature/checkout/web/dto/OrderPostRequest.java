/**
 * Author: Vinod Jagwani
 */
package jp.rhd.cart.feature.checkout.web.dto;


import io.swagger.annotations.ApiModel;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel(value = "OrderPostRequest", description = "OrderPostRequest contains request fields for posting order")
public class OrderPostRequest {

    String userId;

    String sessionId;

    String firstName;

    String lastName;

    String status;

    String email;

    BigDecimal totalAmount;

    String shippingAddress;

}
