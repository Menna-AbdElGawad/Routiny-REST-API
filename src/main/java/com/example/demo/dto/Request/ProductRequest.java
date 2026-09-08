package com.example.demo.dto.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor

public class ProductRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String brand;

    private String description;

}
