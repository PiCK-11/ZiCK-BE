package com.pick.zick.global.security;

public record TokenResponse (
    String accessToken,
    String tokenType,
    String statusCode
){}