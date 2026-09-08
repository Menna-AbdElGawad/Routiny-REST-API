package com.example.demo.dto.Request;

import com.example.demo.entity.Profile;
import com.example.demo.entity.Routine;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor

public class CreateUserRequest {

    @NotBlank
    private String userName;

    @Size(min = 8)
    private String password;

    @NotBlank
    @Email
    private String email;

}
