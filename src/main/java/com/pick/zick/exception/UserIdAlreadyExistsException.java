package com.pick.zick.exception;

public class UserIdAlreadyExistsException extends RuntimeException {
    public static final UserIdAlreadyExistsException EXCEPTION = new UserIdAlreadyExistsException();
    private UserIdAlreadyExistsException(){ super("이미 가입된 아이디입니다"); }
}