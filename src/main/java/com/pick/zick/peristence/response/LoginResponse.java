package com.pick.zick.peristence.response;

public record LoginResponse(String userId, String accessToken, String message) {

    public static LoginResponse ok(String id, String token){
        return new LoginResponse(id, token, "로그인에 성공했습니다.");
    }

    public static LoginResponse wrongPassword(String id){
        return new LoginResponse(id, null, "비밀번호가 올바르지 않습니다.");
    }

    public static LoginResponse notFound() {
        return new LoginResponse(null, null, "존재하지 않는 아이디입니다.");
    }

    public static LoginResponse fail(String message){
        return new LoginResponse(null, null, message);
    }
}
