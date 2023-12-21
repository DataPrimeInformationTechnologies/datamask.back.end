package com.springpostgre.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class DBExceptionController {
    @ExceptionHandler(value = DBNotFoundException.class)
    public ResponseEntity<Object> exception(DBNotFoundException exception) {
        return new ResponseEntity<>("Config not found", HttpStatus.NOT_FOUND);
    }
}