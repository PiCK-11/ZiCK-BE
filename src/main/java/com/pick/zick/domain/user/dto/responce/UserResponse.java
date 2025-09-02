package com.pick.zick.domain.user.dto.responce;

public record UserResponse(
        String userId,
        String name,
        Integer studentNumber
) {
}
