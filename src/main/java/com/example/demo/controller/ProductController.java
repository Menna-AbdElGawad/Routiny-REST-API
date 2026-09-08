package com.example.demo.controller;

import com.example.demo.dto.Request.ProductRequest;
import com.example.demo.dto.Response.ProductResponse;
import com.example.demo.entity.Product;
import com.example.demo.service.Interface.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    // POST /products?routineId=5
    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(
            @Valid @RequestBody ProductRequest productRequest,
            Authentication authentication, @RequestParam Long routineId) {

        return ResponseEntity.status(HttpStatus.CREATED).body(
                productService.addProduct(authentication.getName(), productRequest, routineId)
        );
    }

    // GET /products/{productId}
    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> findProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(
                productService.getProductById(productId)
        );
    }

    // PUT /products/{productId}?routineId=5
    @PatchMapping("/{productId}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Long productId,
            @RequestBody ProductRequest productRequest,
            @RequestParam Long routineId) {

        return ResponseEntity.status(HttpStatus.OK).body(
                productService.updateProduct(productId, productRequest, routineId)
        );
    }

    // DELETE /products/{productId}
    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
    }

}