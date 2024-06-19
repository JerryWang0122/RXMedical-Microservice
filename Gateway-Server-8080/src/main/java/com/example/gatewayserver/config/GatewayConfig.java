package com.example.gatewayserver.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("JWT-Service-9000", r -> r.path("/api/jwt/**").uri("lb://JWT-Service-9000"))
                .route("User-Service-9001", r-> r.path("/api/user/**").uri("lb://User-Service-9001"))
                .build();
    }
}
