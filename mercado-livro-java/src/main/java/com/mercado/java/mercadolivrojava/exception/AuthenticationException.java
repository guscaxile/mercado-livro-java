package com.mercado.java.mercadolivrojava.exception;

public class AuthenticationException extends Exception{

    private final String errorCode;

    public AuthenticationException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
