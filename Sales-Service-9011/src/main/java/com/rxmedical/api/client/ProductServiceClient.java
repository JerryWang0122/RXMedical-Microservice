package com.rxmedical.api.client;

import com.rxmedical.api.model.po.Product;
import com.rxmedical.api.model.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "Product-Service-9006")
public interface ProductServiceClient {
    @GetMapping("/tool/products/{productId}")
    public ApiResponse<Product> findProductById(@PathVariable Integer productId);

    @PutMapping("/tool/products/save")
    public ApiResponse<Object> saveProduct(@RequestBody Product product);
}
