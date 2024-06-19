package com.rxmedical.api.client;

import com.rxmedical.api.model.po.History;
import com.rxmedical.api.model.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "Sales-Service-9011")
public interface SaleServiceClient {
    @PutMapping("/tool/histories/save")
    public ApiResponse<Object> saveHistory(@RequestBody History history);
}
