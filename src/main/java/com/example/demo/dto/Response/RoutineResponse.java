package com.example.demo.dto.Response;

import com.example.demo.entity.Routine;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter

public class RoutineResponse {

    private Long id;

    private String name;

    private String description;

    private Integer frequency;

    private List<ProductResponse> product;

    public RoutineResponse(Routine routine) {
        this.id = routine.getId();
        this.name = routine.getName();
        this.description = routine.getDescription();
        this.frequency = routine.getFrequency();
        this.product = routine.getProduct().stream()
                .map(ProductResponse::new)
                .collect(Collectors.toList());
    }
}
