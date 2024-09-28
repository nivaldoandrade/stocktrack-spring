package com.nasa.stocktrack.infra.exceptions;


import com.nasa.stocktrack.domain.exceptions.EntityExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

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
}
