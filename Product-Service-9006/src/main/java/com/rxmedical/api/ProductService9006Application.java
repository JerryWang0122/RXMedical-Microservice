package com.rxmedical.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ProductService9006Application {

    public static void main(String[] args) {
        SpringApplication.run(ProductService9006Application.class, args);
    }

}
