package com.logistic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.ParameterizedType;
import java.net.URI;
import java.net.URISyntaxException;

import static com.fasterxml.jackson.databind.util.ClassUtil.name;

@RestController
@RequestMapping("/api/home")
public class HomeController {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    EcommerceService ecommerceService;



    HttpHeaders createHeaders() {
        return new HttpHeaders() {{
            set("Authorization", getBearerTokenHeader());
            setContentType(MediaType.APPLICATION_JSON);
        }};
    }

    public static String getBearerTokenHeader() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization");
    }

    @GetMapping("/user")
    public String getuser() throws URISyntaxException {
        String url="http://localhost:8000/api/customer/test_micro_service";
        URI uri=new URI(url);
        HttpEntity<String> httpEntity = new HttpEntity<>(createHeaders());
        ResponseEntity<String> s= restTemplate.exchange(uri, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<String >() {
        });
        return s.getBody();
    }
    @GetMapping("/test")
    public String test1(){
        return ecommerceService.test();
    }



    @GetMapping("/home")
    public String home(String name) {
        name =name("home work");
        return name;
    }



}
