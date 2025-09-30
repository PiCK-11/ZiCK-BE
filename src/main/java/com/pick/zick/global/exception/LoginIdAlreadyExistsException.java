package com.pick.zick.global.exception;

public class LoginIdAlreadyExistsException extends RuntimeException {
    public static final LoginIdAlreadyExistsException EXCEPTION = new LoginIdAlreadyExistsException();
    private LoginIdAlreadyExistsException(){ super("이미 가입된 아이디입니다"); }
}