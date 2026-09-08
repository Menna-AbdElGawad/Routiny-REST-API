package com.example.demo.controller;

import com.example.demo.dto.Request.ProfileRequest;
import com.example.demo.dto.Response.ProfileResponse;
import com.example.demo.dto.Response.RoutineResponse;
import com.example.demo.dto.Response.UserResponse;
import com.example.demo.entity.Routine;
import com.example.demo.service.Interface.ProfileService;
import com.example.demo.service.Interface.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileService profileService;
    private final UserService userService;

    // PATCH /profile
    @PatchMapping
    public ResponseEntity<ProfileResponse> updateProfile(
            @RequestBody ProfileRequest profileRequest, Authentication authentication) {

        String name = authentication.getName();

        UserResponse user = userService.findByUserName(name);
        long userId = user.getId();

        return ResponseEntity.status(HttpStatus.OK).body(
                profileService.updateProfile(profileRequest, userId)
        );
    }

    // GET /profile/routines
    @GetMapping("/routines")
    public ResponseEntity<List<RoutineResponse>> getRoutines(Authentication authentication) {
        String name = authentication.getName();

        return ResponseEntity.ok(
                profileService.findRoutineOfUser(name)
        );
    }
}