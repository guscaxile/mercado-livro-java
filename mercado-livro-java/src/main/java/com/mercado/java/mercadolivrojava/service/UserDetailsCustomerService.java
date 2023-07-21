package com.mercado.java.mercadolivrojava.service;

import com.mercado.java.mercadolivrojava.exception.AuthenticationException;
import com.mercado.java.mercadolivrojava.model.CustomerModel;
import com.mercado.java.mercadolivrojava.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsCustomerService implements UserDetailsService {

    private CustomerRepository customerRepository;

    @Autowired
    public UserDetailsCustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String id) {
        Optional<CustomerModel> optionalCustomer = customerRepository.findById(Integer.parseInt(id));
        CustomerModel customer = optionalCustomer.orElseThrow(() -> new AuthenticationException("Usuário não encontrado.", "999"));
        return new UserCustomDetails(customer);
    }
}
