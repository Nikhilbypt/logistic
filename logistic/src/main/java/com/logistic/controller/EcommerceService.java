package com.logistic.controller;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;


@Service
@FeignClient(name = "ECOMMERCE-SERVICE")
public interface EcommerceService {
    @GetMapping("api/customer/test_micro_service")
    String test();
}
