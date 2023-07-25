package com.mercado.java.mercadolivrojava.controller.request;

import com.mercado.java.mercadolivrojava.validation.EmailAvailable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class PostCustomerRequest {

    @NotEmpty(message = "Nome deve ser informado.")
    private String name;

    @Email(message = "E-mail deve ser válido.")
    @EmailAvailable
    private String email;

    @NotEmpty(message = "Senha deve ser informada.")
    private String password;

    public PostCustomerRequest() {
    }

    public PostCustomerRequest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
