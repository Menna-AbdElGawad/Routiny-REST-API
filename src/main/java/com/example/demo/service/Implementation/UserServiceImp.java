package com.example.demo.service.Implementation;

import com.example.demo.dto.Request.UserRequest;
import com.example.demo.dto.Response.UserResponse;
import com.example.demo.entity.Profile;
import com.example.demo.entity.User;
import com.example.demo.exception.DataNotExists;
import com.example.demo.exception.DuplicateUserName;
import com.example.demo.repository.ProfileRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.Interface.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService{

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserResponse findByUserName(String username) {

        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new DataNotExists("User not found"));

        return new UserResponse(user);
    }

    @Override
    @Transactional
    public UserResponse addUser(UserRequest request) {
        User newUser = new User();

        User user = userRepository.findByUserName(request.getUsername())
                .orElse(null);

        if(user != null) {
            throw new DuplicateUserName("User name already exists");
        }
        newUser.setUserName(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setEmail(request.getEmail());

        Profile profile = new Profile();
        profile.setFullName(request.getFullName());

        Profile savedProfile = profileRepository.save(profile);

        newUser.setProfile(savedProfile);

        User savedUser = userRepository.save(newUser);

        return new UserResponse(savedUser);
    }

    @Override
    @Transactional
    public void deleteUser(String username, String password) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new DataNotExists("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new DataNotExists("Wrong password");
        }

        userRepository.delete(user);
    }

    @Override
    @Transactional
    public UserResponse changePassword(String username, String oldPassword, String newPassword) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new DataNotExists("User not found"));

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new DataNotExists("Wrong password");
        }

        user.setPassword(passwordEncoder.encode(newPassword));

        return new UserResponse(userRepository.save(user));
    }
}