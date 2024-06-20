package com.rxmedical.api.client;

import com.rxmedical.api.model.dto.CSRFVerifyDTO;
import com.rxmedical.api.model.po.User;
import com.rxmedical.api.model.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(name = "JWT-SERVICE-9000")
public interface JWTServiceClient {

    @GetMapping("/api/jwt/CSRFToken")
    public ApiResponse<List<String>> getCSRFToken();

    @PostMapping("/api/jwt/CSRFToken")
    public ApiResponse<Object> checkCSRFToken(@RequestBody CSRFVerifyDTO dto);

    @PostMapping("/api/jwt/userToken")
    public ApiResponse<String> getUserUsageToken(@RequestBody User user);

    @PutMapping("/api/jwt/userToken")
    public ApiResponse<Map<String, Object>> verifyUserUsageToken(@RequestBody String authorizationHeader);
}
