package com.rxmedical.api.client;

import com.rxmedical.api.model.po.Product;
import com.rxmedical.api.model.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "Product-Service-9006")
public interface ProductServiceClient {
//    @GetMapping("/tool/products/{productId}")
//    public ApiResponse<Product> findProductById(@PathVariable Integer productId);

//    @PutMapping("/tool/products/save")
//    public ApiResponse<Object> saveProduct(@RequestBody Product product);

    @GetMapping("/tool/products")
    public ApiResponse<List<Product>> getAllProducts();
}
