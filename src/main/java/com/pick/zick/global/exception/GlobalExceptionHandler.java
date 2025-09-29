package com.pick.zick.global.exception;

import com.pick.zick.global.entity.ErrorResponseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorResponseEntity> handleBusinessException(BusinessException businessException){
        return ErrorResponseEntity.responseEntity(businessException.getErrorCode());
    }
}
