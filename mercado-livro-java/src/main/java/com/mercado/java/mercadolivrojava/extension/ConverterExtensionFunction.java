package com.mercado.java.mercadolivrojava.extension;

import com.mercado.java.mercadolivrojava.controller.request.PostBookRequest;
import com.mercado.java.mercadolivrojava.controller.request.PostCustomerRequest;
import com.mercado.java.mercadolivrojava.controller.request.PutBookRequest;
import com.mercado.java.mercadolivrojava.controller.request.PutCustomerRequest;
import com.mercado.java.mercadolivrojava.controller.response.BookResponse;
import com.mercado.java.mercadolivrojava.controller.response.CustomerResponse;
import com.mercado.java.mercadolivrojava.controller.response.PageResponse;
import com.mercado.java.mercadolivrojava.enums.BookStatus;
import com.mercado.java.mercadolivrojava.enums.CustomerStatus;
import com.mercado.java.mercadolivrojava.model.BookModel;
import com.mercado.java.mercadolivrojava.model.CustomerModel;
import org.springframework.data.domain.Page;

import java.util.stream.Collectors;

public class ConverterExtensionFunction {

    public static CustomerModel toCustomerModel(PostCustomerRequest request){
        return new CustomerModel(
                request.getName(),
                request.getEmail(),
                CustomerStatus.ATIVO,
                request.getPassword()
        );
    }

    public static CustomerModel toCustomerModel(PutCustomerRequest request, CustomerModel previousValue) {
        return new CustomerModel(
                previousValue.getId(),
                request.getName(),
                request.getEmail(),
                previousValue.getStatus(),
                previousValue.getPassword()
        );
    }

    public static BookModel toBookModel(PostBookRequest request, CustomerModel customer) {
        return new BookModel(
                request.getName(),
                request.getPrice(),
                BookStatus.ATIVO,
                customer
        );
    }

    public static BookModel toBookModel(PutBookRequest request, BookModel previousValue) {
        return new BookModel(
                        previousValue.getId(),
                        request.getName() != null ? request.getName() : previousValue.getName(),
                        request.getPrice() != null ? request.getPrice() : previousValue.getPrice(),
                previousValue.getCustomer(),
                previousValue.getStatus()
                );
    }

    public static CustomerResponse toCustomerResponse(CustomerModel model) {
        return new CustomerResponse(
                model.getId(),
                model.getName(),
                model.getEmail(),
                model.getStatus()
        );
    }

    public static BookResponse toBookResponse(BookModel model) {
        return new BookResponse(
                model.getId(),
                model.getName(),
                model.getPrice(),
                model.getCustomer(),
                model.getStatus()
        );
    }

    public static <T> PageResponse<T> toPageResponse(Page<T> page) {
        return new PageResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }

    public static PageResponse<BookResponse> toBookPageResponse(Page<BookModel> page) {
        return new PageResponse<>(
                page.getContent().stream().map(ConverterExtensionFunction::toBookResponse).collect(Collectors.toList()),
                page.getNumber(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }
}
