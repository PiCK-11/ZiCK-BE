package com.pick.zick.domain.student.service;

import com.pick.zick.domain.student.dto.CheckCanEnterResponse;
import com.pick.zick.domain.student.exception.KeyNotFoundException;
import com.pick.zick.domain.student.repository.AttendanceLogRepository;
import com.pick.zick.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CheckCanEnterService {
    private final RedisTemplate<String, String> redisTemplate;
    private final UserRepository userRepository;
    private final AttendanceLogRepository attendanceLogRepository;

    @Transactional
    public CheckCanEnterResponse execute(String key){
        String studentIdStr = redisTemplate.opsForValue().get(key);
        if(studentIdStr == null){
            throw KeyNotFoundException.EXCEPTION;
        }

    }
}
