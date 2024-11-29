/**
 * Author: Vinod Jagwani
 */
package com.user.auth.exception;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public final class UserAuthServiceException extends RuntimeException implements Serializable {

    static final long serialVersionUID = -3034897190745766939L;

    final String message;

    final HttpStatus httpStatus;

    final ErrorPrinter errorEnum;

    public UserAuthServiceException(final ErrorPrinter errorEnum, final String message) {
        super(message);
        this.message = message;
        this.errorEnum = errorEnum;
        this.httpStatus = errorEnum.getHttpStatus();
    }
}
