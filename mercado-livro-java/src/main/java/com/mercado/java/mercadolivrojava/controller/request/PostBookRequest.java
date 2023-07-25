package com.mercado.java.mercadolivrojava.controller.request;

import com.fasterxml.jackson.annotation.JsonAlias;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class PostBookRequest {

    @NotEmpty(message = "Nome deve ser informado.")
    private String name;

    @NotNull(message = "Price deve ser informado.")
    private BigDecimal price;

    @JsonAlias("customer_id")
    private int customerId;

    public PostBookRequest() {
    }

    public PostBookRequest(String name, BigDecimal price, int customerId) {
        this.name = name;
        this.price = price;
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
