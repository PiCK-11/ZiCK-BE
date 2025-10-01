package com.pick.zick.global.exception;

public class PasswordPolicyViolationException extends BusinessException {
    public PasswordPolicyViolationException() {
        super(ErrorCode.PASSWORD_POLICY_VIOLATION);
    }
}