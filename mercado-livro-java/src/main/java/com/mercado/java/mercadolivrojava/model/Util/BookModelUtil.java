package com.mercado.java.mercadolivrojava.model.Util;

import com.mercado.java.mercadolivrojava.controller.response.BookResponse;
import com.mercado.java.mercadolivrojava.model.BookModel;

public class BookModelUtil {

    public static BookResponse toResponse(BookModel bookModel) {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setId(bookModel.getId());
        bookResponse.setName(bookModel.getName());
        bookResponse.setPrice(bookModel.getPrice());
        bookResponse.setCustomer(bookModel.getCustomer());
        bookResponse.setStatus(bookModel.getStatus());
        return bookResponse;
    }
}
