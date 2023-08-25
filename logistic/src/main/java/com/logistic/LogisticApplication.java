package com.logistic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class LogisticApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogisticApplication.class, args);
	}

}
