package com.example.demo.controller;

import com.example.demo.dto.Request.UserRequest;
import com.example.demo.dto.Response.UserResponse;
import com.example.demo.service.Interface.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    // POST /users
    @PostMapping
    public ResponseEntity<UserResponse> registerUser(
            @Valid @RequestBody UserRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED).body(
                userService.addUser(request)
        );

    }

    // GET /users/{username}
    @GetMapping("/{username}")
    public ResponseEntity<UserResponse> findUser(
            @PathVariable String username) {

        return ResponseEntity.status(HttpStatus.OK).body(
                userService.findByUserName(username)
        );

    }

    // PATCH /users/password
    @PatchMapping("/password")
    public ResponseEntity<UserResponse> changePassword(
            @RequestParam String oldPassword, @RequestParam String newPassword,
            Authentication authentication) {

        return ResponseEntity.status(HttpStatus.OK).body(
                userService.changePassword(authentication.getName(), oldPassword, newPassword)
        );

    }

    // DELETE /users
    @DeleteMapping
    public void deleteUser(Authentication authentication, @RequestParam String password) {

        userService.deleteUser(authentication.getName(), password);

    }

}