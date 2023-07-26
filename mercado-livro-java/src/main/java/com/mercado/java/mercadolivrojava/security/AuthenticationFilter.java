package com.mercado.java.mercadolivrojava.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercado.java.mercadolivrojava.controller.request.LoginRequest;
import com.mercado.java.mercadolivrojava.exception.AuthenticationException;
import com.mercado.java.mercadolivrojava.repository.CustomerRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final CustomerRepository customerRepository;
    private final JwtUtil jwtUtil;

    public AuthenticationFilter(
            AuthenticationManager authenticationManager,
            CustomerRepository customerRepository,
            JwtUtil jwtUtil
    ) {
        this.authenticationManager = authenticationManager;
        this.customerRepository = customerRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        try {
            LoginRequest loginRequest = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);
            Integer id = customerRepository.findByEmail(loginRequest.getEmail()).getId();
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(id, loginRequest.getPassword());
            return authenticationManager.authenticate(authToken);
        } catch (IOException ex) {
            throw new AuthenticationException("Falha ao autenticar", "999");
        }
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult
    ) {
        Integer id = ((UserCustomDetails) authResult.getPrincipal()).getId();
        String token = jwtUtil.generateToken(id);
        response.addHeader("Authorization", "Bearer " + token);
    }
}
