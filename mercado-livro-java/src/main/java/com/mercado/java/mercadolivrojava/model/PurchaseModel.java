package com.mercado.java.mercadolivrojava.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "purchase")
public class PurchaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerModel customerModel;

    @ManyToMany
    @JoinTable(name = "purchase_book",
            joinColumns = @JoinColumn(name = "purchase_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<BookModel> books;

    @Column
    private String nfe;

    @Column
    private BigDecimal price;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public PurchaseModel(){
        this.createdAt = LocalDateTime.now();
    }

    public PurchaseModel(Integer id, CustomerModel customerModel, List<BookModel> books, String nfe, BigDecimal price) {
        this.id = id;
        this.customerModel = customerModel;
        this.books = books;
        this.nfe = nfe;
        this.price = price;
    }

    public PurchaseModel(CustomerModel customer, List<BookModel> books, double totalSum) {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CustomerModel getCustomerModel() {
        return customerModel;
    }

    public void setCustomerModel(CustomerModel customerModel) {
        this.customerModel = customerModel;
    }

    public List<BookModel> getBooks() {
        return books;
    }

    public void setBooks(List<BookModel> books) {
        this.books = books;
    }

    public String getNfe() {
        return nfe;
    }

    public void setNfe(String nfe) {
        this.nfe = nfe;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
