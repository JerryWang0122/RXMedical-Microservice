package com.rxmedical.api.client;

import com.rxmedical.api.model.po.User;
import com.rxmedical.api.model.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "User-Service-9001")
public interface UserServiceClient {

    @GetMapping("/tool/users/{userId}")
    public ApiResponse<User> findUserById(@PathVariable Integer userId);
}
