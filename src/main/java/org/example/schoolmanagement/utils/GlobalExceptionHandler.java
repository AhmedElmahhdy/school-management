package org.example.schoolmanagement.utils;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.example.schoolmanagement.dto.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFound(
            ResourceNotFoundException ex) {
        log.error("Resource not found: {}", ex.getMessage());

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequest(
            BadRequestException ex) {
        log.error("Bad request: {}", ex.getMessage());

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<?> handleUnauthorized(
            UnauthorizedException ex) {
        log.error("Unauthorized: {}", ex.getMessage());

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<?> handleForbidden(
            ForbiddenException ex) {
        log.error("Forbidden: {}", ex.getMessage());

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<?> handleConflict(
            ConflictException ex) {
        log.error("Conflict: {}", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<Map<String, String>>> handleValidationException(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        BaseResponse<Map<String, String>> response = new BaseResponse<>();
        response.setStatusCode(HttpStatus.BAD_REQUEST.value());
        response.setStatusMessage("Validation failed");
        response.setData(errors);

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolation(
            ConstraintViolationException ex, HttpServletRequest request) {
        log.error("Constraint violation: {}", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleTypeMismatch(
            MethodArgumentTypeMismatchException ex) {
        log.error("Type mismatch: {}", ex.getMessage());

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<String>> handleGenericException(Exception ex) {
        log.error("Unexpected error", ex);

        BaseResponse<String> response = new BaseResponse<>();
        response.setStatusCode(500);
        response.setStatusMessage("Internal server error");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<BaseResponse<?>> handleBusinessException(BusinessException ex) {
        log.error("message: {}, details: {}", ex.getMessage(), ex.getDetails(), ex);
        BaseResponse<?> response = new BaseResponse<>();
        response.setStatusCode(ex.getCode());
        response.setStatusMessage(ex.getMessage());
        response.setDetails(ex.getDetails());

        return ResponseEntity.status(ex.getCode()).body(response);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String, Object>> handleAccessDeniedException(AccessDeniedException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.FORBIDDEN.value());
        response.put("error", "Forbidden");
        response.put("message", "You do not have permission to access this resource");

        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<Object> handleAuthorizationDenied(AuthorizationDeniedException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("statusCode", 403);
        body.put("statusMessage", "Forbidden: Access is denied");
        body.put("details", ex.getMessage());
        body.put("data", null);

        return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
    }

}
