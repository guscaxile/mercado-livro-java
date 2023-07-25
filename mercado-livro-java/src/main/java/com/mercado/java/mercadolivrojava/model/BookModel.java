package com.mercado.java.mercadolivrojava.model;

import com.mercado.java.mercadolivrojava.enums.BookStatus;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "book")
public class BookModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerModel customer;

    @Column
    @Enumerated(EnumType.STRING)
    private BookStatus status;

    public BookModel(Integer id, String name, BigDecimal price, CustomerModel customer, BookStatus status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.customer = customer;
        this.status = status;
    }

    public BookModel(String name, BigDecimal price, BookStatus bookStatus, CustomerModel customer) {
    }

    public BookModel() {

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
