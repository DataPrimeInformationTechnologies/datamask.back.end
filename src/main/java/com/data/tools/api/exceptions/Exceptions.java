package com.data.tools.api.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum Exceptions {

    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "User not found"),
    USER_NOT_LOGIN(HttpStatus.FORBIDDEN, "User not login"),
    USER_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "User already exist"),
    ID_NOT_FOUND(HttpStatus.BAD_REQUEST, "Id not found"),
    OBJECT_NOT_FOUND_BY_ID(HttpStatus.NOT_FOUND, "Object not found by Id"),
    OBJECT_NOT_FOUND(HttpStatus.NOT_FOUND, "Object not found"),
    INTERVAL_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error occurred. Admins were informed");

    private final HttpStatus status;
    private String message;

    private Exceptions(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
