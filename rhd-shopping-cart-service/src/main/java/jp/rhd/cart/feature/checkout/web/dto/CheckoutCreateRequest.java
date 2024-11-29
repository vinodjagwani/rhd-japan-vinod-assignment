/**
 * Author: Vinod Jagwani
 */
package jp.rhd.cart.feature.checkout.web.dto;


import io.swagger.annotations.ApiModel;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel(value = "CheckoutCreateRequest", description = "CheckoutCreateRequest contains request fields for creating checkout")
public class CheckoutCreateRequest {
}
