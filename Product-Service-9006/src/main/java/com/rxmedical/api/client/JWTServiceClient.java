package com.rxmedical.api.client;

import com.rxmedical.api.model.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "JWT-SERVICE-9000")
public interface JWTServiceClient {
    @PutMapping("/api/jwt/userToken")
    public ApiResponse<Map<String, Object>> verifyUserUsageToken(@RequestBody String authorizationHeader);
}
