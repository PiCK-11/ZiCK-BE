package com.pick.zick.domain.student.service;

import com.pick.zick.domain.user.entity.User;
import com.pick.zick.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetRandomHash {
    private final RedisTemplate<String, String> redisTemplate;
    private final UserFacade userFacade;

    public String makeHash() {
        User user = userFacade.getCurrentUser();
        Long studentId = user.getId();
        System.out.println(studentId);

        return "최선재";
    }
}
