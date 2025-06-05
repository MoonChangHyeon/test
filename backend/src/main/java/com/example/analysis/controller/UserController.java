package com.example.analysis.controller;

import com.example.analysis.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/me")
    public Map<String, Object> me(Authentication authentication) {
        return userRepository.findByUsername(authentication.getName())
                .map(u -> {
                    Map<String, Object> userInfo = new java.util.HashMap<>();
                    userInfo.put("id", u.getId());
                    userInfo.put("username", u.getUsername());
                    userInfo.put("role", u.getRole());
                    return userInfo;
                })
                .orElseThrow();
    }
}
