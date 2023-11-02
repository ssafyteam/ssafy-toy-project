package com.weareone.quicklog.service.impl;

import com.weareone.quicklog.dto.JwtToken;
import com.weareone.quicklog.dto.request.LoginRequest;
import com.weareone.quicklog.dto.request.UserDtoRequest;
import com.weareone.quicklog.dto.response.UserDtoResponse;
import org.springframework.stereotype.Service;

public interface UserService {
//    String signup(UserDtoRequest userDtoRequest);
    JwtToken login(LoginRequest loginRequest);
    UserDtoResponse updateUserInfo(UserDtoRequest userDtoRequest, long id);
}
