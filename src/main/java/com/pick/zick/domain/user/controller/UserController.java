package com.pick.zick.domain.user.controller;

import com.pick.zick.domain.user.entity.User;
import com.pick.zick.domain.user.repository.UserRepository;
import com.pick.zick.domain.user.service.UserDeleteService;
import com.pick.zick.global.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody User user) {
        // 단순 저장 (테스트용)
        User saved = userRepository.save(user);
        return ResponseEntity.ok(saved);
    }

    private final UserDeleteService userDeleteService;

    @DeleteMapping("/me")
    public ApiResponse delete(Authentication auth) {
        userDeleteService.delete(auth);

        return ApiResponse.success(null);
    }
}

