package com.pick.zick.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, Object>> handleBusinessException(BusinessException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("type", ex.getErrorCode().name());
        body.put("statusCode", ex.getErrorCode().getStatus());
        body.put("message", ex.getErrorCode().getMessage());
        return ResponseEntity.status(ex.getErrorCode().getStatus()).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("type", "VALIDATION_ERROR");
        body.put("statusCode", HttpStatus.BAD_REQUEST.value());
        body.put("message", "요청 값이 올바르지 않습니다.");
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgument(IllegalArgumentException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("type", "INVALID_ARGUMENT");
        body.put("statusCode", HttpStatus.BAD_REQUEST.value());
        body.put("message", ex.getMessage());
        return ResponseEntity.badRequest().body(body);
    }
}
