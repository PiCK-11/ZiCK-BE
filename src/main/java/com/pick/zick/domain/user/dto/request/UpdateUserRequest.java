package com.pick.zick.domain.user.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public record UpdateUserRequest(
    @Size(min = 1, max = 50) String userName,
    @Min(1000) @Max(9999) Integer studentNumber,
    Boolean verified
) {}
