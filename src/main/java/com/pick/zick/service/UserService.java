package com.pick.zick.service;

import com.pick.zick.domain.entity.User;
import com.pick.zick.domain.repository.UserRepository;
import com.pick.zick.exception.BusinessException;
import com.pick.zick.exception.ErrorCode;
import com.pick.zick.peristence.response.UserResponse;
import com.pick.zick.peristence.request.UpdateUserRequest;
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

        user.updateProfile(req.name(), req.studentNumber());

        return new UserResponse(user.getUserId(), user.getName(), user.getStudentNumber());
    }
}
