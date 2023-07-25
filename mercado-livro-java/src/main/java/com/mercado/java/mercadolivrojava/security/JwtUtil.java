package com.mercado.java.mercadolivrojava.security;

import com.mercado.java.mercadolivrojava.exception.AuthenticationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(int id){
        return Jwts.builder()
                .setSubject(Integer.toString(id))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .compact();
    }

    public boolean isValidToken(String token){
        Claims claims = getClaims(token);
        if (claims.getSubject() == null || claims.getExpiration() == null || new Date().after(claims.getExpiration())){
            return false;
        }
        return true;
    }

    private Claims getClaims(String token){
        try {
            return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
        } catch (Exception ex) {
            throw new AuthenticationException("Token inválido", "999");
        }
    }

    public String getSubject(String token){
        return getClaims(token).getSubject();
    }
}
