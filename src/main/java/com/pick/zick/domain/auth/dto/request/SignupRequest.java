package com.pick.zick.domain.auth.dto.request;

import jakarta.validation.constraints.*;
import com.pick.zick.domain.user.persistence.entity.User.Role;

public record SignupRequest(
        @Pattern(regexp = "^[A-Za-z0-9]{1,16}$",
                message = "아이디는 영문자+숫자 조합 16자 이내여야 합니다.")
        String userId,

        @NotBlank(message = "이름은 필수입니다.")
        @Size(max = 30, message = "이름은 30자 이하여야 합니다.")
        String userName,

        @NotBlank @Size(min = 8, max = 64, message = "비밀번호는 8~64자여야 합니다.")
        @Pattern(
                regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()\\-_=+<>?]).{8,64}$",
                message = "비밀번호는 영문, 숫자, 특수문자를 포함하여 8~64자여야 합니다."
        )
        String password,

        @NotBlank(message = "비밀번호 재확인은 필수입니다.")
        String confirmPassword,

        @NotBlank(message = "학번은 필수입니다.")
        @Pattern(regexp = "^[0-9]{4,10}$", message = "학번은 숫자 4~10자리여야 합니다.")
        Integer studentNumber,

        com.pick.zick.domain.user.persistence.entity.User.Role role
) {}