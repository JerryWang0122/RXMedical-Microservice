package com.example.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServer9050Application {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServer9050Application.class, args);
    }

}
