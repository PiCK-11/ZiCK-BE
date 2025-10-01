package com.pick.zick.domain.user.facade;

import com.pick.zick.domain.user.entity.Role;
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

    public String getCurrentLoginId() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public Role getRole() {
        return getCurrentUser().getRole();
    }

    public User getCurrentUser() {
        return getUserByLoginId(getCurrentLoginId());
    }

    public User getUserByLoginId(String loginId) {
        return userRepository.findByLoginId(loginId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }
}
