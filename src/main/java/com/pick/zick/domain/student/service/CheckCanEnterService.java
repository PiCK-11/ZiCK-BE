package com.pick.zick.domain.student.service;

import com.pick.zick.domain.student.dto.CheckCanEnterResponse;
import com.pick.zick.domain.student.entity.MealType;
import com.pick.zick.domain.student.exception.KeyNotFoundException;
import com.pick.zick.domain.student.repository.AttendanceLogRepository;
import com.pick.zick.domain.user.exception.UserNotFoundException;
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
        Long studentId = Long.valueOf(studentIdStr);

        Boolean applied = userRepository.findById(studentId).orElseThrow(() -> UserNotFoundException.EXCEPTION).getApplied();
        boolean status = applied != null && applied; //신청 시 true, 아니면 false
    }
}
