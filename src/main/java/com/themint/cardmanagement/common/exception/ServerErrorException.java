package com.themint.cardmanagement.common.exception;


public class ServerErrorException extends RuntimeException {

    public ServerErrorException(final String exception) {
        super(exception);
    }
}
