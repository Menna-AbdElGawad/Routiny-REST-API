package com.example.demo.dto.Request;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class RoutineRequest {

    @NotBlank
    private String name;

    private String description;

    private Integer frequency;

}
