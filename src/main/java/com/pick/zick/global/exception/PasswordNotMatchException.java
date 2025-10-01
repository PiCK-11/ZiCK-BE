package com.pick.zick.global.exception;

public class PasswordNotMatchException extends BusinessException {
    public PasswordNotMatchException() {
        super(ErrorCode.PASSWORD_NOT_MATCH);
    }
}