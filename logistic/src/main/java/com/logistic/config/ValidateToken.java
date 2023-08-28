package com.logistic.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ValidateToken {

    @Value("${jwt.id}")
    private String jwtId;

    public String validateToken(String id){
        if(id.equalsIgnoreCase(jwtId)){
            return jwtId;
        }else{
            return null;
        }
    }
}
