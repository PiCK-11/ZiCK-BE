package com.pick.zick.domain.user.persistence.dto.response;

import com.pick.zick.domain.user.entity.User;
import lombok.Getter;

@Getter
public class CafeteriaMyPageResponse implements MyPageResponse {
    private final String userName;
    private final String loginId;

    public CafeteriaMyPageResponse(User user) {
        this.userName = user.getUserName();
        this.loginId = user.getLoginId();
    }
}