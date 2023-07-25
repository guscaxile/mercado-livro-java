package com.mercado.java.mercadolivrojava.controller.response;

import java.util.List;

public class PageResponse<T> {

    private List<T> items;
    private int currentPage;
    private long totalItems;
    private int totalPages;

    public PageResponse() {
    }

    public PageResponse(List<T> items, int currentPage, long totalItems, int totalPages) {
        this.items = items;
        this.currentPage = currentPage;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
