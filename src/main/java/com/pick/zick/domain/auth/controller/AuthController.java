package com.pick.zick.domain.auth.controller;

import com.pick.zick.domain.auth.dto.request.LoginRequest;
import com.pick.zick.domain.auth.dto.request.SignupRequest;
import com.pick.zick.domain.auth.dto.response.LoginResponse;
import com.pick.zick.domain.auth.dto.response.SignupResponse;
import com.pick.zick.domain.auth.service.AuthService;
import jakarta.validation.Valid;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(@Valid @RequestBody SignupRequest req) {
        SignupResponse res = authService.signup(req);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest req) {
        return ResponseEntity.ok(authService.login(req));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest req) {
        authService.logout(req);
        return ResponseEntity.noContent().build();
    }
}
