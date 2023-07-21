package com.mercado.java.mercadolivrojava.exception;

public class NotFoundException extends Exception {

    private final String errorCode;

    public NotFoundException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
