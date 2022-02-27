package com.example.guerraalantico.Excepciones;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.DateTimeException;

@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(value = { ApiException.class })
    protected ResponseEntity<ApiError> handleApiException(ApiException e) {
        Integer statusCode = e.getStatusCode();
        boolean expected = HttpStatus.INTERNAL_SERVER_ERROR.value() > statusCode;
        if (expected) {
            LOGGER.warn("Internal Api warn. Status Code: " + statusCode, e);
        } else {
            LOGGER.error("Internal Api error. Status Code: " + statusCode, e);
        }

        ApiError apiError = new ApiError(e.getCode(), e.getDescription(), statusCode);
        return ResponseEntity.status(apiError.getStatus())
                .body(apiError);
    }

    @ExceptionHandler(value = { DateTimeException.class })
    protected ResponseEntity<ApiError> handleApiException(DateTimeException e) {
        Integer statusCode = HttpStatus.BAD_REQUEST.value();
        boolean expected = HttpStatus.INTERNAL_SERVER_ERROR.value() > statusCode;;
        LOGGER.error("Internal Api error. Status Code: " + statusCode, e);

        ApiError apiError = new ApiError("error", e.getMessage(), statusCode);
        return ResponseEntity.status(apiError.getStatus())
                .body(apiError);
    }

}
