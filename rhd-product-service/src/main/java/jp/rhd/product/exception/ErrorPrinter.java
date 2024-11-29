/**
 * Author: Vinod Jagwani
 */
package jp.rhd.product.exception;

import org.springframework.http.HttpStatus;

public interface ErrorPrinter {

    HttpStatus getHttpStatus();

}
