package com.pick.zick.domain.user.facade;

import com.pick.zick.domain.user.entity.Role;
import com.pick.zick.domain.user.entity.User;
import com.pick.zick.domain.user.exception.UserNotFoundException;
import com.pick.zick.domain.user.repository.UserRepository;
import com.pick.zick.global.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public Role getRoleFromJwt(String token) {
        String loginId = jwtProvider.getLoginId(token);
        return userRepository.findByLoginId(loginId)
                .map(User::getRole)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    public User getCurrentUser() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        return getUserById(userId);
    }

    public User getUserById(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }
}
