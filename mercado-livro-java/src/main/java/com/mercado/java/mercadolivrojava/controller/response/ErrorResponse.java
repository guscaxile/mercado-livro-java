package com.mercado.java.mercadolivrojava.controller.response;

import java.util.List;

public class ErrorResponse {

    private int httpCode;
    private String message;
    private String internalCode;
    private List<FieldErrorResponse> errors;

    public ErrorResponse() {
    }

    public ErrorResponse(int httpCode, String message, String internalCode, List<FieldErrorResponse> errors) {
        this.httpCode = httpCode;
        this.message = message;
        this.internalCode = internalCode;
        this.errors = errors;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getInternalCode() {
        return internalCode;
    }

    public void setInternalCode(String internalCode) {
        this.internalCode = internalCode;
    }

    public List<FieldErrorResponse> getErrors() {
        return errors;
    }

    public void setErrors(List<FieldErrorResponse> errors) {
        this.errors = errors;
    }
}
