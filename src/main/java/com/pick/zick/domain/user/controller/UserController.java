package com.pick.zick.domain.user.controller;

import com.pick.zick.domain.user.persistence.dto.response.MyPageResponse;
import com.pick.zick.domain.user.service.GetMyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final GetMyPageService getMyPageService;

    @GetMapping("/users/me")
    public MyPageResponse getUserByUserName() {
        return getMyPageService.getMyPage();
    }
}