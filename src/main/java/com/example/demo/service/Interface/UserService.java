package com.example.demo.service.Interface;

import com.example.demo.dto.Request.UserRequest;
import com.example.demo.dto.Response.UserResponse;

public interface UserService {

    UserResponse findByUserName(String username);

    UserResponse addUser(UserRequest user);

    void deleteUser(String username, String password);

    UserResponse changePassword(String username, String oldPassword, String newPassword);
}
