package com.pick.zick.global.exception;

public class UserIdAlreadyExistsException extends BusinessException {
    public UserIdAlreadyExistsException() {
        super(ErrorCode.USER_ID_ALREADY_EXISTS);
    }
}