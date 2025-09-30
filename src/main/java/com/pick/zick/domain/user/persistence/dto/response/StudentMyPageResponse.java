package com.pick.zick.domain.user.persistence.dto.response;

import com.pick.zick.domain.user.entity.User;
import lombok.Getter;

@Getter
public class StudentMyPageResponse implements MyPageResponse {
    private final String studentNumber;
    private final String userName;
    private final String loginId;
    private final Boolean applied;
    private final Boolean verified;

    public StudentMyPageResponse(User user) {
        this.studentNumber = user.getStudentNumber();
        this.userName = user.getUserName();
        this.loginId = user.getLoginId();
        this.applied = user.getApplied();
        this.verified = user.getVerified();
    }
}
