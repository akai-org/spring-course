package pl.org.akai.springsecurityrest.web;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.org.akai.springsecurityrest.security.InvalidTokenException;


@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidTokenException.class)
    protected ResponseEntity<Object> handleIllegalUserException(InvalidTokenException ex) {
        ErrorResponse apiError = new ErrorResponse(HttpStatus.UNAUTHORIZED, "Invalid token");
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
