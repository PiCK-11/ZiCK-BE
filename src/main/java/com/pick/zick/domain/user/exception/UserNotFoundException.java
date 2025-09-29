package com.pick.zick.domain.user.exception;

import com.pick.zick.global.exception.BusinessException;
import com.pick.zick.global.exception.ErrorCode;

public class UserNotFoundException extends BusinessException {
    public static final BusinessException EXCEPTION = new UserNotFoundException();
    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
