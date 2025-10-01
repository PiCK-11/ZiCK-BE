package com.pick.zick.domain.student.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CheckCanEnterResponse {
    private final boolean canEnter;
    private final String loginId;
}
