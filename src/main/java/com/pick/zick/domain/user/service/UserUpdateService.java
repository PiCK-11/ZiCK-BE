package com.pick.zick.domain.user.service;

import com.pick.zick.domain.user.entity.User;
import com.pick.zick.domain.user.repository.UserRepository;
import com.pick.zick.global.exception.BusinessException;
import com.pick.zick.global.exception.ErrorCode;
import com.pick.zick.domain.user.dto.response.UserResponse;
import com.pick.zick.domain.user.dto.request.UpdateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserUpdateService {
    private final UserRepository userRepository;

    @Transactional
    public UserResponse updateMyProfile(String loginId, UpdateUserRequest req) {
        User user = userRepository.findById(loginId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        user.updateProfile(req.userName(), req.password());

        return new UserResponse(
                user.getLoginId(),
                user.getUserName(),
                user.getStudentNumber(),
                user.isVerified()
        );
    }
}

