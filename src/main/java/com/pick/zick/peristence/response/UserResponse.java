package com.pick.zick.peristence.response;

public record UserResponse(
        String userId,
        String name,
        Integer studentNumber
) {
}
