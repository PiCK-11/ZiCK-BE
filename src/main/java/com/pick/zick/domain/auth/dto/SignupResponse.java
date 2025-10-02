package com.pick.zick.domain.auth.dto;

public record SignupResponse(String loginId, String message) {
    public static SignupResponse ok(String id){
        return new SignupResponse(id, "회원가입이 완료되었습니다.");
    }
}

