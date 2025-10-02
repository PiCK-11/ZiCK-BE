package com.pick.zick.domain.user.dto.response;

public record UserResponse(
        String loginId,
        String userName,
        Integer studentNumber,
        Boolean verified
) {
}
