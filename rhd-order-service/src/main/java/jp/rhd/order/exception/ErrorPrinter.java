/**
 * Author: Vinod Jagwani
 */
package jp.rhd.order.exception;

import org.springframework.http.HttpStatus;

public interface ErrorPrinter {

    HttpStatus getHttpStatus();

}
