/**
 * Author: Vinod Jagwani
 */
package com.user.auth.security.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel(value = "UserLoginRequest", description = "UserLoginRequest contains request fields for login user")
public class UserLoginRequest {

    @NotEmpty
    @ApiModelProperty(name = "username", example = "testAB")
    String username;

    @Email
    @NotEmpty
    @ApiModelProperty(name = "email", example = "testAB@gmail.com")
    String email;

    @NotEmpty
    @ApiModelProperty(name = "password", example = "12345")
    String password;

}
