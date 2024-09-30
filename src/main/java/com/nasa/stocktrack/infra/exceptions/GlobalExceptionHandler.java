package com.nasa.stocktrack.infra.exceptions;


import com.nasa.stocktrack.domain.exceptions.EntityExistsException;
import com.nasa.stocktrack.domain.exceptions.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<RestErrorResponse> handleEntityNotFoundException(EntityNotFoundException e) {
        int status = HttpStatus.NOT_FOUND.value();

        RestErrorResponse error = new RestErrorResponse(
                status,
                e.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<RestErrorResponse> handleEntityExistsException(EntityExistsException e) {
        int status = HttpStatus.CONFLICT.value();

        RestErrorResponse error = new RestErrorResponse(
                status,
                e.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestErrorResponseWithErrors>
    handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        int statusCode = HttpStatus.BAD_REQUEST.value();

        List<ApiError> errors = e.getBindingResult().getFieldErrors().stream()
                .map(ex -> {
                    String pointer = ex.getField();

                    int t = Objects.requireNonNull(ex.getDefaultMessage()).indexOf(";");

                    String reason = t != -1
                            ? ex.getDefaultMessage().substring(t + 1).trim()
                            : ex.getDefaultMessage();

                    return new ApiError(pointer, reason);

                })
                .toList();
        RestErrorResponseWithErrors error = new RestErrorResponseWithErrors(
                statusCode,
                errors,
                LocalDateTime.now()
        );


        return ResponseEntity.status(statusCode).body(error);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<RestErrorResponseWithErrors> HandlerMethodValidationException(HandlerMethodValidationException e) {
        int statusCode = HttpStatus.BAD_REQUEST.value();

        List<ApiError> errors = new ArrayList<>();

        e.getAllValidationResults().forEach(validator -> {
            String parameterName = validator.getMethodParameter().getParameterName();

            validator.getResolvableErrors().forEach(ve -> {
                errors.add(new ApiError(parameterName, ve.getDefaultMessage()));
            });
        });


        RestErrorResponseWithErrors error = new RestErrorResponseWithErrors(
                statusCode,
                errors,
                LocalDateTime.now()
        );

        return ResponseEntity.status(statusCode).body(error);

    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<RestErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        int statusCode = HttpStatus.BAD_REQUEST.value();

        String message = String.format("The parameter %s is invalid", e.getName());

        RestErrorResponse error = new RestErrorResponse(
                statusCode,
                message,
                LocalDateTime.now()
        );

        return ResponseEntity.status(statusCode).body(error);
    }

}
