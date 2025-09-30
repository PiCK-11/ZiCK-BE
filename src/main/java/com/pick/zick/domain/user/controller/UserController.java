package com.pick.zick.domain.user.controller;

import com.pick.zick.domain.user.entity.User;
import com.pick.zick.domain.user.persistence.dto.response.MyPageResponse;
import com.pick.zick.domain.user.repository.UserRepository;
import com.pick.zick.domain.user.service.GetUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final GetUserService getUserService;

    @GetMapping("/users/me")
    public MyPageResponse getUserByUserName() {
        return getUserService.getMyPage();
    }
}