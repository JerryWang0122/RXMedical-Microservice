package com.rxmedical.api.client;

import com.rxmedical.api.model.po.User;
import com.rxmedical.api.model.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "User-Service-9001")
public interface UserServiceClient {
    @PostMapping("/api/users/tools/findUser")
    public ApiResponse<User> findUserById(@RequestBody Integer userId);
}
