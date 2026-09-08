package com.example.demo.repository;

import com.example.demo.dto.Response.ProductResponse;
import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<ProductResponse> findByRoutineId(Long routineId);
}
