package com.pick.zick.domain.auth.dto.response;

import com.pick.zick.domain.user.persistence.entity.User.Role;

public record SignupResponse(
        String userId,
        String accessToken,
        String message,
        Role role,
        boolean verifield
) {
    public static SignupResponse ok(String id, String accessToken, Role role, boolean verifield) {
        return new SignupResponse(id, accessToken, "회원가입이 완료되었습니다.", role, verifield);
    }
}
