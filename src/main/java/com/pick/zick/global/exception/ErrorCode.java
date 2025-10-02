package com.pick.zick.global.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    USER_ID_ALREADY_EXISTS("이미 존재하는 아이디 입니다"),
    PASSWORD_NOT_MATCH("비밀번호가 일치하지 않습니다"),
    PASSWORD_POLICY_VIOLATION("비밀번호 정책 위반입니다"),
    USER_NOT_FOUND("존재하지 않는 아이디 입니다");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }
}
