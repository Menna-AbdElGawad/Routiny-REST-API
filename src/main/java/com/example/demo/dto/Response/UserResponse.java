package com.example.demo.dto.Response;

import com.example.demo.entity.Routine;
import com.example.demo.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class UserResponse {

    private Long id;

    private String userName;

    private String email;

    private ProfileResponse profile;

    public UserResponse(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.profile = new ProfileResponse(user.getProfile());
    }
}
