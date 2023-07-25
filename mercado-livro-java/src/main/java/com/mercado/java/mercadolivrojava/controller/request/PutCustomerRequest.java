package com.mercado.java.mercadolivrojava.controller.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class PutCustomerRequest {

    @NotEmpty(message = "Nome deve ser informado.")
    private String name;

    @Email(message = "E-mail deve ser válido.")
    private String email;

    public PutCustomerRequest() {
    }

    public PutCustomerRequest(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
