package com.mercado.java.mercadolivrojava.controller.response;

public class FieldErrorResponse {

    private String name;
    private String field;

    public FieldErrorResponse() {
    }

    public FieldErrorResponse(String name, String field) {
        this.name = name;
        this.field = field;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
