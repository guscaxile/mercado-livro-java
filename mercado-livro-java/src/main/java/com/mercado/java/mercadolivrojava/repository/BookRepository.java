package com.mercado.java.mercadolivrojava.repository;

import com.mercado.java.mercadolivrojava.model.BookModel;
import com.mercado.java.mercadolivrojava.model.CustomerModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<BookModel, Integer> {
    public Page<BookModel> findByStatus(BookStatus status, Pageable pageable);

    public List<BookModel> findByCustomer(CustomerModel customer);
}
