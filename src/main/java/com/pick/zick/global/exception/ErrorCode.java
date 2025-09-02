package com.pick.zick.global.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    USER_NOT_FOUND(404, "사용자가 존재하지 않습니다."),
    USER_ALREADY_EXISTS(409, "이미 존재하는 아이디입니다."),
    PASSWORD_NOT_MATCH(401, "비밀번호가 일치하지 않습니다."),
    PASSWORD_POLICY_VIOLATION(400, "비밀번호가 정책에 맞지 않습니다.");

    private final int status;
    private final String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}