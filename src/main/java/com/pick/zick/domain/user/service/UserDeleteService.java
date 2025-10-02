package com.pick.zick.domain.user.service;


import com.pick.zick.domain.user.repository.UserRepository;
import com.pick.zick.global.exception.BusinessException;
import com.pick.zick.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDeleteService {
    private final UserRepository userRepository;

    @Transactional
    public void delete(Authentication auth) {
        String loginId = auth.getName();
        if (!userRepository.existsByloginId(loginId)) {
            throw new BusinessException(ErrorCode.USER_NOT_FOUND);
        }
        userRepository.deleteById(loginId);
    }
}
