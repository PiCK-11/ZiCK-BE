package com.pick.zick.domain.auth.dto.response;

import com.pick.zick.domain.user.persistence.entity.User.Role;

public record LoginResponse(String userId, String accessToken, String message, Role role) {

    public static LoginResponse ok(String id, String token, Role role) {
        return new LoginResponse(id, token, "로그인에 성공했습니다.", role);
    }

    public static LoginResponse wrongPassword(String id){
        return new LoginResponse(id, null, "비밀번호가 올바르지 않습니다.", null);
    }

    public static LoginResponse notFound() {
        return new LoginResponse(null, null, "존재하지 않는 아이디입니다.", null);
    }

    public static LoginResponse fail(String message){
        return new LoginResponse(null, null, message, null);
    }
}
