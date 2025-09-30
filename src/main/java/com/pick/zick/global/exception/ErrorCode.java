package com.pick.zick.global.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
    USER_NOT_FOUND("userNotFound", 404),
    INTERNAL_SERVER_ERROR("internalServerError", 500);
    private final String type;
    private final int statusCode;
}
