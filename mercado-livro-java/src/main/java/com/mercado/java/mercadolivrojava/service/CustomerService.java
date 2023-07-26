package com.mercado.java.mercadolivrojava.service;

import com.mercado.java.mercadolivrojava.enums.CustomerStatus;
import com.mercado.java.mercadolivrojava.enums.Errors;
import com.mercado.java.mercadolivrojava.enums.Role;
import com.mercado.java.mercadolivrojava.exception.NotFoundException;
import com.mercado.java.mercadolivrojava.model.CustomerModel;
import com.mercado.java.mercadolivrojava.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final BookService bookService;
    private final BCryptPasswordEncoder bCrypt;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, BookService bookService, BCryptPasswordEncoder bCrypt) {
        this.customerRepository = customerRepository;
        this.bookService = bookService;
        this.bCrypt = bCrypt;
    }

    public Page<CustomerModel> getAll(String name, Pageable pageable) {
        if (name != null && name.isEmpty()) {
            return customerRepository.findByNameContaining(name, pageable);
        }
        return customerRepository.findAll(pageable);
    }

    public CustomerModel findById(Integer id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        Errors.ML201.getMessage().replace("%s", String.valueOf(id)), Errors.ML201.getCode())
                );
    }

    public void create(CustomerModel customer) {
        Set<Role> roles = new HashSet<>();
        roles.add(Role.CUSTOMER);
        CustomerModel customerCopy = new CustomerModel(
                null,
                customer.getName(),
                customer.getEmail(),
                customer.getStatus(),
                bCrypt.encode(customer.getPassword()),
                roles
        );
        customerRepository.save(customerCopy);
    }

    public void update(CustomerModel customer) {
        if (!customerRepository.existsById(customer.getId())) {
            throw new RuntimeException("Customer with ID " + customer.getId() + " not found.");
        }
        customerRepository.save(customer);
    }

    public void delete(Integer id) {
        CustomerModel customer = findById(id);
        bookService.deleteByCustomer(customer);
        customer.setStatus(CustomerStatus.INATIVO);
        customerRepository.save(customer);
    }

    public boolean emailAvailable(String email) {
        return !customerRepository.existsByEmail(email);
    }
}
