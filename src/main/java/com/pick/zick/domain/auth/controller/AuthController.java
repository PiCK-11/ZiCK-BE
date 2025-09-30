package com.pick.zick.domain.auth.controller;

import com.pick.zick.domain.auth.dto.LoginRequest;
import com.pick.zick.domain.auth.dto.LoginResponse;
import com.pick.zick.domain.auth.dto.SignupRequest;
import com.pick.zick.domain.auth.service.AuthService;
import com.pick.zick.global.security.TokenResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public TokenResponse signup(@Valid @RequestBody SignupRequest request) {
        return authService.signup(request);
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody @Valid LoginRequest req) {
        return authService.login(req);
    }
}