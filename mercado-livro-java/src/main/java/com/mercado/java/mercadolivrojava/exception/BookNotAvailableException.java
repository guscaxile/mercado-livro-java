package com.mercado.java.mercadolivrojava.exception;

public class BookNotAvailableException extends RuntimeException {

    private final String errorCode;

    public BookNotAvailableException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
