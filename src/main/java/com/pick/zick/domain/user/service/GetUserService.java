package com.pick.zick.domain.user.service;

import com.pick.zick.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserService {
    private final UserFacade userFacade;


}
