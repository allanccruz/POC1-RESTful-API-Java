package com.github.allanccruz.POC1RESTfulAPI.api.exceptions;

import com.github.allanccruz.POC1RESTfulAPI.api.dto.response.ErrorResponse;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException exception, WebRequest request) {

        ErrorResponse response = new ErrorResponse();

        response.setHttpCode(HttpStatus.NOT_FOUND.value());
        response.setMessage(exception.getMessage());
        response.setInternalCode(exception.getErrorCode());
        response.setPath(request.getDescription(false));
        response.setTimestamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

    }

    @ExceptionHandler(InvalidZipcodeException.class)
    public ResponseEntity<ErrorResponse> handleInvalidZipcodeException(InvalidZipcodeException exception, WebRequest request) {

        ErrorResponse response = new ErrorResponse();

        response.setHttpCode(HttpStatus.BAD_REQUEST.value());
        response.setMessage(exception.getMessage());
        response.setInternalCode(exception.getErrorCode());
        response.setPath(request.getDescription(false));
        response.setTimestamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

    }

    @ExceptionHandler(LimitOfAddressesException.class)
    public ResponseEntity<ErrorResponse> handleLimitOfAddressesException(LimitOfAddressesException exception, WebRequest request) {

        ErrorResponse response = new ErrorResponse();

        response.setHttpCode(HttpStatus.CONFLICT.value());
        response.setMessage(exception.getMessage());
        response.setInternalCode(exception.getErrorCode());
        response.setPath(request.getDescription(false));
        response.setTimestamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);

    }

}
