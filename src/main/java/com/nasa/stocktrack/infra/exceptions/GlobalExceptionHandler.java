package com.nasa.stocktrack.infra.exceptions;


import com.nasa.stocktrack.domain.exceptions.DuplicateWarehouseIdException;
import com.nasa.stocktrack.domain.exceptions.EntityExistsException;
import com.nasa.stocktrack.domain.exceptions.EntityInUseException;
import com.nasa.stocktrack.domain.exceptions.EntityNotFoundException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @ExceptionHandler(DuplicateWarehouseIdException.class)
    public ResponseEntity<RestErrorResponseWithErrors> handleDuplicateWarehouseIdException(DuplicateWarehouseIdException e) {
        int statusCode = HttpStatus.BAD_REQUEST.value();

        List<ApiError> apiErrors = List.of(new ApiError(e.getPointer(), e.getReason()));
        RestErrorResponseWithErrors error = new RestErrorResponseWithErrors(
                statusCode,
                apiErrors,
                LocalDateTime.now()
        );

        return ResponseEntity.status(statusCode).body(error);
    }

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

    @ExceptionHandler(EntityInUseException.class)
    public ResponseEntity<RestErrorResponse> handleEntityInUseException(EntityInUseException e) {
        int status = HttpStatus.UNPROCESSABLE_ENTITY.value();

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

    @ExceptionHandler(FileStorageException.class)
    public ResponseEntity<RestErrorResponse> handleFileStorageException(FileStorageException e) {
        int statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();

        RestErrorResponse error = new RestErrorResponse(
          statusCode,
          e.getMessage(),
          LocalDateTime.now()
        );

        return ResponseEntity.status(statusCode).body(error);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<RestErrorResponse> handleFileNotFoundException(FileNotFoundException e) {
        int status = HttpStatus.NOT_FOUND.value();

        RestErrorResponse error = new RestErrorResponse(
                status,
                e.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<RestErrorResponse> handleBadCredentialsException(BadCredentialsException e) {
        int statusCode = HttpStatus.BAD_REQUEST.value();

        RestErrorResponse error = new RestErrorResponse(
                statusCode,
                "The username or password is invalid",
                LocalDateTime.now()
        );

        return ResponseEntity.status(statusCode).body(error);
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<RestErrorResponse> handleMalformedJwtException(MalformedJwtException e) {
        int statusCode = HttpStatus.BAD_REQUEST.value();

        RestErrorResponse error = new RestErrorResponse(
                statusCode,
                "The JWT format is invalid",
                LocalDateTime.now()
        );

        return ResponseEntity.status(statusCode).body(error);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<RestErrorResponse> handleSignatureException(SignatureException e) {
        int statusCode = HttpStatus.UNAUTHORIZED.value();

        RestErrorResponse error = new RestErrorResponse(
                statusCode,
                "The JWT signature is invalid",
                LocalDateTime.now()
        );

        return ResponseEntity.status(statusCode).body(error);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<RestErrorResponse> handleExpiredJwtException(ExpiredJwtException e) {
        int statusCode = HttpStatus.UNAUTHORIZED.value();

        RestErrorResponse error = new RestErrorResponse(
                statusCode,
                "The JWT token has expired",
                LocalDateTime.now()
        );

        return ResponseEntity.status(statusCode).body(error);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<RestErrorResponse> handleExpiredJwtException(UsernameNotFoundException e) {
        int statusCode = HttpStatus.UNAUTHORIZED.value();

        RestErrorResponse error = new RestErrorResponse(
                statusCode,
                e.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(statusCode).body(error);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<RestErrorResponse> handleAccessDeniedException(AccessDeniedException e) {
        int statusCode = HttpStatus.FORBIDDEN.value();

        RestErrorResponse error = new RestErrorResponse(
                statusCode,
                "You are not authorized to access this resource",
                LocalDateTime.now()
        );

        return ResponseEntity.status(statusCode).body(error);
    }
}
