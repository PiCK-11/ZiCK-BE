package com.pick.zick.domain.student.exception;

import com.pick.zick.global.exception.BusinessException;
import com.pick.zick.global.exception.ErrorCode;

public class KeyNotFoundException extends BusinessException {
  public static final BusinessException EXCEPTION = new KeyNotFoundException();
  public KeyNotFoundException() {
    super(ErrorCode.KEY_NOT_FOUND);
  }
}
