package com.springpostgre.project.exceptions;

public class DBNotFoundException extends RuntimeException {
    public DBNotFoundException(String message) {
        super(message);
    }
}
