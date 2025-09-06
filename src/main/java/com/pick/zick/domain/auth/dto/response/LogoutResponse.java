package com.pick.zick.domain.auth.dto.response;

public record LogoutResponse(int statusCode) {
    public static LogoutResponse ok() { return new LogoutResponse(200); }
}
