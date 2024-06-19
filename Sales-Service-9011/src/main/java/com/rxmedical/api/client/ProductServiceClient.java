package com.rxmedical.api.client;

import com.rxmedical.api.model.po.Product;
import com.rxmedical.api.model.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "Product-Service-9006")
public interface ProductServiceClient {
    @PostMapping("/api/products/tools/findProduct")
    public ApiResponse<Product> findProductById(@RequestBody Integer productId);

    @PutMapping("/api/products/tools/saveProduct")
    public ApiResponse<Object> saveProduct(@RequestBody Product product);
}
