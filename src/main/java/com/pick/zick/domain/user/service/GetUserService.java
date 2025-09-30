package com.pick.zick.domain.user.service;

import com.pick.zick.domain.user.entity.Role;
import com.pick.zick.domain.user.entity.User;
import com.pick.zick.domain.user.exception.InternalServerError;
import com.pick.zick.domain.user.facade.UserFacade;
import com.pick.zick.domain.user.persistence.dto.response.CafeteriaMyPageResponse;
import com.pick.zick.domain.user.persistence.dto.response.MyPageResponse;
import com.pick.zick.domain.user.persistence.dto.response.StudentMyPageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserService {
    private final UserFacade userFacade;

    public MyPageResponse getMyPage(){
        Role role = userFacade.getRole();

        return switch (role) {
            case STUDENT -> getStudentMyPage();
            case CAFETERIA -> getCafeteriaMyPage();
            default -> throw InternalServerError.EXCEPTION;
        };
    }

    private MyPageResponse getStudentMyPage(){
        User user = userFacade.getCurrentUser();
        return new StudentMyPageResponse(user);
    }

    private MyPageResponse getCafeteriaMyPage(){
        User user = userFacade.getCurrentUser();
        return new CafeteriaMyPageResponse(user);
    }
}