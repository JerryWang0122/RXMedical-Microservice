package com.rxmedical.api.controller;

import com.rxmedical.api.model.po.User;
import com.rxmedical.api.model.response.ApiResponse;
import com.rxmedical.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tool/users")
public class ToolController {
    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<User>> findUserById(@PathVariable Integer userId) {
        User user = userService.findUserById(userId);
        return ResponseEntity.ok(new ApiResponse<>(true, "使用者資訊", user));
    }
}
