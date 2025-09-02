package com.pick.zick.peristence.request;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

public record UpdateUserRequest(
        @Size(min = 1, max = 50) String name,
        @Min(1000) @Max(9999) Integer studentNumber
) { }