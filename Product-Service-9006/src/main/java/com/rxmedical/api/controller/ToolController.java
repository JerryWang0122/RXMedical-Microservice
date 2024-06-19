package com.rxmedical.api.controller;

import com.rxmedical.api.model.po.Product;
import com.rxmedical.api.model.response.ApiResponse;
import com.rxmedical.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tool/products")
public class ToolController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{productId}")
    public ResponseEntity<ApiResponse<Product>> findProductById(@PathVariable Integer productId) {
        return ResponseEntity.ok(new ApiResponse<>(true, "產品資訊", productService.findProductById(productId)));
    }

    @PutMapping("/save")
    public ResponseEntity<ApiResponse<Object>> saveProduct(@RequestBody Product product) {
        productService.saveProduct(product);
        return ResponseEntity.ok(new ApiResponse<>(true, "儲存成功", null));
    }
}
