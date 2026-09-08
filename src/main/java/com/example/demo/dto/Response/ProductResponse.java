package com.example.demo.dto.Response;

import com.example.demo.entity.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ProductResponse {

    private Long id;

    private String name;

    private String description;

    private String brand;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.brand = product.getBrand();
    }
}
