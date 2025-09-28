package com.pick.zick.domain.user.service;

import com.pick.zick.domain.user.persistence.entity.User;
import com.pick.zick.domain.user.persistence.repository.UserRepository;
import com.pick.zick.global.exception.BusinessException;
import com.pick.zick.global.exception.ErrorCode;
import com.pick.zick.domain.user.dto.responce.UserResponse;
import com.pick.zick.domain.user.dto.request.UpdateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserResponse updateMyProfile(String userId, UpdateUserRequest req) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        user.updateProfile(req.userName(), req.studentNumber());

        return new UserResponse(user.getUserId(), user.getUserName(), user.getStudentNumber(), user.isVerifield());
    }
}
