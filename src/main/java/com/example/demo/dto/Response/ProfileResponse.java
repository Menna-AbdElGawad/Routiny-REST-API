package com.example.demo.dto.Response;

import com.example.demo.entity.Profile;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter

public class ProfileResponse {

    private Long id;

    private String fullName;

    private String profilePicture;

    private List<RoutineResponse> routine;

    public ProfileResponse(Profile profile) {
        this.id = profile.getId();
        this.fullName = profile.getFullName();
        this.profilePicture = profile.getProfilePicture();
        this.routine = profile.getRoutine().stream()
                .map(RoutineResponse::new)
                .collect(Collectors.toList());
    }

}
