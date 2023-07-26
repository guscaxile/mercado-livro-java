package com.mercado.java.mercadolivrojava.controller.mapper;

import com.mercado.java.mercadolivrojava.controller.request.PostPurchaseRequest;
import com.mercado.java.mercadolivrojava.model.PurchaseModel;
import com.mercado.java.mercadolivrojava.service.BookService;
import com.mercado.java.mercadolivrojava.service.CustomerService;
import org.springframework.stereotype.Component;

@Component
public class PurchaseMapper {

    private final BookService bookService;
    private final CustomerService customerService;

    public PurchaseMapper(BookService bookService, CustomerService customerService) {
        this.bookService = bookService;
        this.customerService = customerService;
    }

    public PurchaseModel toModel(PostPurchaseRequest request) {
        var customer = customerService.findById(request.getCustomerId());
        var books = bookService.findAllById(request.getBookIds());
        var totalSum = books.stream().mapToDouble(book -> book.getPrice().doubleValue()).sum();
        return new PurchaseModel(customer, books, totalSum);
    }
}
