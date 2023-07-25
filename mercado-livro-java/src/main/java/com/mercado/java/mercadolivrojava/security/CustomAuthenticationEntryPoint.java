package com.mercado.java.mercadolivrojava.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercado.java.mercadolivrojava.controller.response.ErrorResponse;
import com.mercado.java.mercadolivrojava.enums.Errors;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), Errors.ML000.getMessage(), Errors.ML000.getCode(), null);
        ObjectMapper objectMapper = new ObjectMapper();
        response.getOutputStream().print(objectMapper.writeValueAsString(errorResponse));
    }
}
