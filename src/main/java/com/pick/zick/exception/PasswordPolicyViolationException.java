package com.pick.zick.exception;

public class PasswordPolicyViolationException extends RuntimeException {
    public PasswordPolicyViolationException(){ super("비밀번호가 정책을 충족하지 않습니다"); }
}