package com.pick.zick.domain.user.dto.responce;

public record UserResponse(
        String userId,
        String userName,
        Integer studentNumber
) {
}
