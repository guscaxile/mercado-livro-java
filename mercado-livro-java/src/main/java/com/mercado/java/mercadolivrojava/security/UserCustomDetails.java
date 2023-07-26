package com.mercado.java.mercadolivrojava.security;

import com.mercado.java.mercadolivrojava.enums.CustomerStatus;
import com.mercado.java.mercadolivrojava.model.CustomerModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class UserCustomDetails implements UserDetails {

    private final CustomerModel customerModel;

    public UserCustomDetails(CustomerModel customerModel) {
        this.customerModel = customerModel;
    }

    public int getId() {
        return customerModel.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return customerModel.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getDescription()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return customerModel.getPassword();
    }

    @Override
    public String getUsername() {
        return String.valueOf(customerModel.getId());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return customerModel.getStatus() == CustomerStatus.ATIVO;
    }
}
