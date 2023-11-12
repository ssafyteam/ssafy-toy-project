package com.weareone.quicklog.service;

import com.weareone.quicklog.dto.JwtToken;
import com.weareone.quicklog.dto.request.LoginRequest;
import com.weareone.quicklog.dto.request.UserDtoRequest;
import com.weareone.quicklog.dto.request.UserInfoRequest;
import com.weareone.quicklog.dto.request.UserSignUpDto;
import com.weareone.quicklog.dto.response.UserDtoResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {
    String signup(UserSignUpDto userSignUpDto) throws Exception;
    @Transactional
    Void updateUser(String token, UserInfoRequest userInfoRequest) throws Exception;
    Long deleteUser(String token);
    JwtToken login(LoginRequest loginRequest);
}
