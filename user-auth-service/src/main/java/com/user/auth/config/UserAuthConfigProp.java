/**
 * Author: Vinod Jagwani
 */
package com.user.auth.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Validated
@ConfigurationProperties(prefix = "app")
public class UserAuthConfigProp {

    @NotEmpty
    String userIdInitialPrefix;

    @NotEmpty
    String userIdDatePrefix;

    @NotNull
    Integer userIdMaxLimit;
}


