package com.pick.zick.presistence.response;

public record SignupResponse(String userId, String message) {
    public static SignupResponse ok(String id){
        return new SignupResponse(id, "회원가입이 완료되었습니다.");
    }
}

