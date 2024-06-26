package com.rxmedical.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SalesService9011Application {

    public static void main(String[] args) {
        SpringApplication.run(SalesService9011Application.class, args);
    }

}
