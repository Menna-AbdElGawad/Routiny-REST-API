package com.example.demo.service.Implementation;

import com.example.demo.dto.Request.ProfileRequest;
import com.example.demo.dto.Response.ProfileResponse;
import com.example.demo.dto.Response.RoutineResponse;
import com.example.demo.entity.Profile;
import com.example.demo.entity.User;
import com.example.demo.exception.DataNotExists;
import com.example.demo.repository.ProfileRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.Interface.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileServiceImp implements ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ProfileResponse updateProfile(ProfileRequest profileRequest, long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new DataNotExists("User not found")
        );

        Profile profile = user.getProfile();

       if(profile == null) {
           throw new DataNotExists("Profile not found");
       }

       if(profileRequest.getProfilePicture() != null) {
           profile.setProfilePicture(profileRequest.getProfilePicture());
       }

       if(profileRequest.getFullName() != null) {
           profile.setFullName(profileRequest.getFullName());
       }

       profileRepository.save(profile);
       user.setProfile(profile);
       userRepository.save(user);

       return new ProfileResponse(profile);

    }

    @Override
    @Transactional
    public List<RoutineResponse> findRoutineOfUser(String username) {
        User user = userRepository.findByUserName(username).orElseThrow(
                () -> new DataNotExists("User not found")
        );

        Profile profile = user.getProfile();

        return profile.getRoutine()
                .stream()
                .map(RoutineResponse::new)
                .toList();
    }
}
