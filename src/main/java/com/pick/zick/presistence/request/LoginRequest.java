package com.pick.zick.presistence.request;

import jakarta.validation.constraints.*;

public record LoginRequest(
        @NotBlank
        @Pattern(regexp = "^[A-Za-z0-9]{1,16}$", message = "아이디 형식이 올바르지 않습니다.")
        String userId,
        @NotBlank(message = "비밀번호를 입력해주세요.")
        String password
) {}