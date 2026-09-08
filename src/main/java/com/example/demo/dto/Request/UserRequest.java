package com.example.demo.dto.Request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor

public class UserRequest {

    @NotBlank
    private String username;

    @Size(min = 8)
    @Valid
    private String password;

    @Email
    private String email;

    @NotBlank
    private String fullName;

}

