/**
 * Author: Vinod Jagwani
 */
package com.user.auth.exception.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {

    int code;
    String message;
    List<ErrorInfo> errors;

    @Value
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ErrorInfo {
        String domain;
        String reason;
        String message;
    }
}
