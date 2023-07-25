package com.mercado.java.mercadolivrojava.security;

import com.mercado.java.mercadolivrojava.exception.AuthenticationException;
import com.mercado.java.mercadolivrojava.service.UserDetailsCustomerService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationFilter extends BasicAuthenticationFilter {

    private final UserDetailsCustomerService userDetails;
    private final JwtUtil jwtUtil;

    public AuthorizationFilter(
            AuthenticationManager authenticationManager,
            UserDetailsCustomerService userDetails,
            JwtUtil jwtUtil
    ) {
        super(authenticationManager);
        this.userDetails = userDetails;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer ")){
            UsernamePasswordAuthenticationToken auth = getAuthentication(authorization.split(" ")[1]);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token){
        if (!jwtUtil.isValidToken(token)){
            throw new AuthenticationException("Token inválido", "999");
        }
        String subject = jwtUtil.getSubject(token);
        UserCustomDetails customer = (UserCustomDetails) userDetails.loadUserByUsername(subject);
        return new UsernamePasswordAuthenticationToken(customer, null, customer.getAuthorities());
    }
}
