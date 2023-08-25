package com.logistic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.fasterxml.jackson.databind.util.ClassUtil.name;

@RestController
@RequestMapping("/api/home")
public class HomeController {



    @GetMapping("/home")
    public String home(String name){
        name =name("home work");
        return name;
    }
}
