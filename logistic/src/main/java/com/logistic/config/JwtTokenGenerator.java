package com.logistic.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;

@Configuration
public class JwtTokenGenerator {

    @Autowired
    private JwtUtils jwtUtils;

    @Value("${jwt.id}")
    private String jwtId;

    public String getTokenByUserName(UserDetails userDetails, EcommerceUser ecommerceUser) {
        final String token = jwtUtils.generateToken(userDetails, jwtId, ecommerceUser);
        return token;
    }
}
