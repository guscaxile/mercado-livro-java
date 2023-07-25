package com.mercado.java.mercadolivrojava.controller.request;

import com.fasterxml.jackson.annotation.JsonAlias;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Set;

public class PostPurchaseRequest {

    @NotNull
    @Positive
    @JsonAlias("customer_id")
    private int customerId;

    @NotNull
    @JsonAlias("book_ids")
    private Set<Integer> bookIds;

    public PostPurchaseRequest() {
    }

    public PostPurchaseRequest(int customerId, Set<Integer> bookIds) {
        this.customerId = customerId;
        this.bookIds = bookIds;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Set<Integer> getBookIds() {
        return bookIds;
    }

    public void setBookIds(Set<Integer> bookIds) {
        this.bookIds = bookIds;
    }
}
