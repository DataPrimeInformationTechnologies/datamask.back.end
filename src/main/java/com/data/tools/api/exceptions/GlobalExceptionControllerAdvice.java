package com.data.tools.api.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionControllerAdvice {

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<ExceptionResponse> globalException(GlobalException ex) {
        log.error("type: {} msg: {}", ex.getExceptions().name(), ex.getLogMessage());
        return ResponseEntity.status(ex.getExceptions().getStatus()).body(new ExceptionResponse(ex.getExceptions().name(), ex.getMessage()));
    }
}
