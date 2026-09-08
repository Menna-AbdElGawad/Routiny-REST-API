package com.example.demo.service.Interface;

import com.example.demo.dto.Request.ProductRequest;
import com.example.demo.dto.Response.ProductResponse;
import com.example.demo.entity.Product;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductService {
    ProductResponse addProduct(String userName, ProductRequest productRequest, Long routineId);


    @Transactional
    void deleteProduct(Long productId);

    @Transactional
    ProductResponse updateProduct(Long productId, ProductRequest productRequest, Long routineId);

    ProductResponse getProductById(Long productId);

    List<ProductResponse> getProductsRoutine(Long routineId);
}
