/**
 * Author: Vinod Jagwani
 */
package jp.rhd.cart.exception;

import org.springframework.http.HttpStatus;

public interface ErrorPrinter {

    HttpStatus getHttpStatus();

}
