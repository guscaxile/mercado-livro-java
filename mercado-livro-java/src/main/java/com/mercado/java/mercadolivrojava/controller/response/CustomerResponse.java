package com.mercado.java.mercadolivrojava.controller.response;

import com.mercado.java.mercadolivrojava.enums.CustomerStatus;

public class CustomerResponse {

    private Integer id;
    private String name;
    private String email;
    private CustomerStatus status;

    public CustomerResponse() {
    }

    public CustomerResponse(Integer id, String name, String email, CustomerStatus status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public CustomerStatus getStatus() {
        return status;
    }

    public void setStatus(CustomerStatus status) {
        this.status = status;
    }
}
