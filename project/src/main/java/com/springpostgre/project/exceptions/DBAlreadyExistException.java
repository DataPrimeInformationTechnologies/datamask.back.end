package com.springpostgre.project.exceptions;

public class DBAlreadyExistException extends RuntimeException{
    public DBAlreadyExistException(String message){
        super(message);
    }
}
