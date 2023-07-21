package com.mercado.java.mercadolivrojava.service;

import com.mercado.java.mercadolivrojava.enums.BookStatus;
import com.mercado.java.mercadolivrojava.enums.Errors;
import com.mercado.java.mercadolivrojava.exception.NotFoundException;
import com.mercado.java.mercadolivrojava.model.BookModel;
import com.mercado.java.mercadolivrojava.model.CustomerModel;
import com.mercado.java.mercadolivrojava.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void create(BookModel book) {
        bookRepository.save(book);
    }

    public Page<BookModel> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    public Page<BookModel> findActives(Pageable pageable) {
        return bookRepository.findByStatus(BookStatus.ATIVO, pageable);
    }

    public BookModel findById(int id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format(Errors.ML101.getMessage(), id),
                        Errors.ML101.getCode()
                ));
    }

    public void delete(int id){
        BookModel book = findById(id);
        book.setStatus(BookStatus.CANCELADO);
        update(book);
    }

    public void update(BookModel book){
        bookRepository.save(book);
    }

    public void deleteByCustomer(CustomerModel customer){
        List<BookModel> books = bookRepository.findByCustomer(customer);
        for (BookModel book : books){
            book.setStatus(BookStatus.DELETADO);
        }
        bookRepository.saveAll(books);
    }

    public List<BookModel> findAllById(Set<Integer> bookIds){
        return bookRepository.findAllById(bookIds);
    }


    public void purchase(List<BookModel> books){
        for (BookModel book : books){
            book.setStatus(BookStatus.VENDIDO);
        }
        bookRepository.saveAll(books);
    }
}
