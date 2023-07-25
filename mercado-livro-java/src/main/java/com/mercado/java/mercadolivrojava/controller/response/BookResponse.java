package com.mercado.java.mercadolivrojava.controller.response;

import com.mercado.java.mercadolivrojava.enums.BookStatus;
import com.mercado.java.mercadolivrojava.model.CustomerModel;

import java.math.BigDecimal;

public class BookResponse {

    private Integer id;
    private String name;
    private BigDecimal price;
    private CustomerModel customer;
    private BookStatus status;

    public BookResponse() {
    }

    public BookResponse(Integer id, String name, BigDecimal price, CustomerModel customer, BookStatus status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.customer = customer;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }
}
