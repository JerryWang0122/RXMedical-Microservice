package com.rxmedical.api.controller;

import com.nimbusds.jose.JOSEException;
import com.rxmedical.api.model.dto.CSRFVerifyDTO;
import com.rxmedical.api.model.po.User;
import com.rxmedical.api.model.response.ApiResponse;
import com.rxmedical.api.service.JWTService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/jwt")
public class JWTController {

    @Autowired
    private JWTService jwtService;

    @GetMapping("/test")
    public String test() {
        return "JWT test";
    }

    // 取得CSRFToken
    @GetMapping("/CSRFToken")
    public ResponseEntity<ApiResponse<List<String>>> getCSRFToken() throws JOSEException {
        return ResponseEntity.ok(new ApiResponse<>(true, "CSRFToken", jwtService.getCSRFTokenJWT()));
    }

    // 驗證CSRFToken
    @PostMapping("/CSRFToken")
    public ResponseEntity<ApiResponse<Object>> checkCSRFToken(@RequestBody CSRFVerifyDTO dto) throws ParseException {
        boolean result = jwtService.checkCSRFTokenJWT(dto.token(), dto.authorizationHeader());
        if (result) {
            return ResponseEntity.ok(new ApiResponse<>(true, "CSRF Token 驗證通過", null));
        } else {
            return ResponseEntity.ok(new ApiResponse<>(false, "CSRF Token 驗證失敗", null));
        }
    }

    // 取得使用者權限Token
    @PostMapping("/userToken")
    public ResponseEntity<ApiResponse<String>> getUserUsageToken(@RequestBody User user) throws JOSEException {
        return ResponseEntity.ok(new ApiResponse<>(true, "User Token", jwtService.getUserUsageJWT(user)));
    }

    // 驗證並提取使用者Token資料
    @PutMapping("/userToken")
    public ResponseEntity<ApiResponse<Map<String, Object>>> verifyUserUsageToken(@RequestBody String authorizationHeader) throws ParseException {
        Map<String, Object> userInfo = jwtService.verifyUserUsageJWT(authorizationHeader);
        if (userInfo == null) {
            return ResponseEntity.ok(new ApiResponse<>(false, "User Token 驗證失敗", null));
        } else {
            return ResponseEntity.ok(new ApiResponse<>(true, "User Token 驗證通過", userInfo));
        }
    }

}
