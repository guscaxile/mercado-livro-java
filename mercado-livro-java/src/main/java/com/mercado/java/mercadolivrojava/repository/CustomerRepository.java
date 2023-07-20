package com.mercado.java.mercadolivrojava.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerModel, Integer> {
    Page<CustomerModel> findByNameContaining(String name, Pageable pageable);
    Boolean existsByEmail(String email);
    CustomerModel findByEmail(String email);
}
