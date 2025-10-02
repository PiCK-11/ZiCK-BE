package com.pick.zick.domain.student.service;

import com.pick.zick.domain.user.entity.User;
import com.pick.zick.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MealResetScheduler {
    private final UserRepository userRepository;

    //아침 식사 종료 후 초기화
    @Scheduled(cron = "0 0 9 * * *")
    @Transactional
    public void resetBreakfast(){
        resetVerifications();
    }

    //점심
    @Scheduled(cron = "0 0 13 * * *")
    @Transactional
    public void resetLunch(){
        resetVerifications();
    }

    @Scheduled(cron = "0 0 19 * * *")
    @Transactional
    public void resetDinner(){
        resetVerifications();
    }

    private void resetVerifications(){
        List<User> users = userRepository.findAll();
        for(User user : users){
            user.updateVerified(false);
        }
    }
}
