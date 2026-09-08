package com.example.demo.dto.Request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class ProfileRequest {

    private String fullName;

    private String profilePicture;
}
