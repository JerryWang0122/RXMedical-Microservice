package com.rxmedical.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UserService9001Application {

    public static void main(String[] args) {
        SpringApplication.run(UserService9001Application.class, args);
    }

}
