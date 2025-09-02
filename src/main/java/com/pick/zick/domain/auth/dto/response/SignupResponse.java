package com.pick.zick.domain.auth.dto.response;

public record SignupResponse(String userId, String accessToken, String message) {
    public static SignupResponse ok(String id, String accessToken){
        return new SignupResponse(id, accessToken, "회원가입이 완료되었습니다.");
    }
}
