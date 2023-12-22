package com.data.tools.api.exceptions;

public class DBAlreadyExistException extends RuntimeException{
    public DBAlreadyExistException(String message){
        super(message);
    }
}
