package com.pick.zick.domain.student.service;

import com.pick.zick.domain.student.dto.response.CheckCanEnterResponse;
import com.pick.zick.domain.student.entity.AttendanceLog;
import com.pick.zick.domain.student.entity.MealType;
import com.pick.zick.domain.student.exception.KeyNotFoundException;
import com.pick.zick.domain.student.repository.AttendanceLogRepository;
import com.pick.zick.domain.user.entity.User;
import com.pick.zick.domain.user.exception.UserNotFoundException;
import com.pick.zick.domain.user.facade.UserFacade;
import com.pick.zick.domain.user.repository.UserRepository;
import com.pick.zick.global.util.MealTypeUtil;
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
    private final UserFacade userFacade;

    @Transactional
    public CheckCanEnterResponse execute(String key){
        String studentIdStr = redisTemplate.opsForValue().get(key);
        if(studentIdStr == null){
            throw KeyNotFoundException.EXCEPTION;
        }
        Long studentId = Long.valueOf(studentIdStr);

        Boolean applied = userRepository.findById(studentId).orElseThrow(() -> UserNotFoundException.EXCEPTION).getApplied();
        String loginId = userFacade.getCurrentLoginId();
        boolean status = applied != null && applied; //신청 시 true, 아니면 false

        MealType mealType = MealTypeUtil.getCurrentMealType();

        User user = userRepository.findById(studentId).orElseThrow(() -> UserNotFoundException.EXCEPTION);

        AttendanceLog log = new AttendanceLog(user, mealType, status);
        attendanceLogRepository.save(log);

        //출입 성공 시 verified를 true로 변경하는 코드 작성

        return new CheckCanEnterResponse(status, loginId);
    }
}
