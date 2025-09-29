package com.pick.zick.domain.user.facade;

import com.pick.zick.domain.user.entity.User;
import com.pick.zick.domain.user.exception.UserNotFoundException;
import com.pick.zick.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade {
    private final UserRepository userRepository;

    public User getCurrentUser() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        return getUserById(userId);
    }

    public User getUserById(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }
}
