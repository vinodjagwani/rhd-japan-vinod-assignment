/**
 * Author: Vinod Jagwani
 */
package com.user.auth.exception.constant;

import com.user.auth.exception.ErrorPrinter;
import org.springframework.http.HttpStatus;

public enum ErrorCodeEnum implements ErrorPrinter {

    INVALID_PARAMETER(HttpStatus.BAD_REQUEST),
    USER_ID_SEQ_COMPLETE(HttpStatus.BAD_REQUEST),
    ENTITY_NOT_FOUND(HttpStatus.BAD_REQUEST);

    ErrorCodeEnum(final HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    private final HttpStatus httpStatus;

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}