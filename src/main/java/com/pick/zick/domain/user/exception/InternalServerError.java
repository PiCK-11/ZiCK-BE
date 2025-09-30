package com.pick.zick.domain.user.exception;

import com.pick.zick.global.exception.BusinessException;
import com.pick.zick.global.exception.ErrorCode;

public class InternalServerError extends BusinessException {
    public static final BusinessException EXCEPTION = new InternalServerError();
    public InternalServerError() {
        super(ErrorCode.INTERNAL_SERVER_ERROR);
    }
}
