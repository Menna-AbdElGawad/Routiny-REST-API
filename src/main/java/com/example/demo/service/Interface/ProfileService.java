package com.example.demo.service.Interface;

import com.example.demo.dto.Request.ProfileRequest;
import com.example.demo.dto.Response.ProfileResponse;
import com.example.demo.dto.Response.RoutineResponse;

import java.util.List;

public interface ProfileService {

    ProfileResponse updateProfile(ProfileRequest profileRequest, long userId);

    List<RoutineResponse> findRoutineOfUser(String username);

}
