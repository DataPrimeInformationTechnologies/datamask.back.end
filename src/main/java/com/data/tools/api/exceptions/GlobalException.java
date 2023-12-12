package com.data.tools.api.exceptions;

import lombok.Getter;

@Getter
public class GlobalException extends RuntimeException {

    private final Exceptions exceptions;

    private final String logMessage;

    public GlobalException(Exceptions exceptions, String logMessage) {
        super(logMessage);
        this.exceptions = exceptions;
        this.logMessage = logMessage;
    }

    public static RuntimeException throwEx(Exceptions exceptions, String logMessage) {
        return new GlobalException(exceptions, logMessage);
    }

}
