package com.weareone.quicklog.service.impl;

import com.weareone.quicklog.dto.JwtToken;
import com.weareone.quicklog.dto.Role;
import com.weareone.quicklog.dto.request.LoginRequest;
import com.weareone.quicklog.dto.request.UserDtoRequest;
import com.weareone.quicklog.dto.request.UserInfoRequest;
import com.weareone.quicklog.dto.request.UserSignUpDto;
import com.weareone.quicklog.dto.response.UserDtoResponse;
import com.weareone.quicklog.entity.Token;
import com.weareone.quicklog.entity.User;
import com.weareone.quicklog.exception.ResourceNotFoundException;
import com.weareone.quicklog.repository.TokenRepository;
import com.weareone.quicklog.repository.UserRepository;
import com.weareone.quicklog.security.JwtTokenProvider;
import com.weareone.quicklog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;
    @Override
    public String signup(UserSignUpDto userSignUpDto) throws Exception {

        if (userRepository.findByEmail(userSignUpDto.getEmail()).isPresent()) {
            throw new Exception("이미 존재하는 이메일입니다.");
        }

        if (userRepository.findByNickname(userSignUpDto.getNickname()).isPresent()) {
            throw new Exception("이미 존재하는 닉네임입니다.");
        }

        User user = User.builder()
                .email(userSignUpDto.getEmail())
                .password(userSignUpDto.getPassword())
                .nickname(userSignUpDto.getNickname())
                .name(userSignUpDto.getName())
                .birth(userSignUpDto.getBirth())
                .role(Role.USER)
                .build();

        userRepository.save(user);
        return "회원가입 성공";
    }

    @Override
    public void updateUser(String token, UserInfoRequest userInfoRequest) throws Exception {

        String email = jwtTokenProvider.getEmail(token);
        Optional<User> optUser = Optional.of(userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("해당 이메일이 존재하지 않습니다.")));
        if (userRepository.findByEmail(userInfoRequest.getEmail()).isPresent()) {
            throw new Exception("이미 존재하는 이메일입니다.");
        }

        if (userRepository.findByNickname(userInfoRequest.getNickname()).isPresent()) {
            throw new Exception("이미 존재하는 닉네임입니다.");
        }
        optUser.get().update(userInfoRequest.getEmail(), userInfoRequest.getPassword(), userInfoRequest.getBirth(),userInfoRequest.getName(),userInfoRequest.getNickname());
    }

    @Override
    public Long deleteUser(String token) {
        String email = jwtTokenProvider.getEmail(token);
        Long cnt = userRepository.deleteByEmail(email);
        return cnt;
    }

    @Override
    public JwtToken login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));

        if (!loginRequest.getPassword().equals(user.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        JwtToken jwtToken = jwtTokenProvider.generateToken(user.getEmail());
        tokenRepository.save(new Token(jwtToken.getRefreshToken()));
        return jwtToken;
    }

    @Override
    public UserDtoResponse getUser(String token) {
        String email = jwtTokenProvider.getEmail(token);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
        UserDtoResponse userDtoResponse = UserDtoResponse.builder().email(user.getEmail())
                        .name(user.getName()).nickName(user.getNickname())
                        .birth(user.getBirth()).build();
        return userDtoResponse;
    }

    @Override
    public Long deleteRefreshToken(String token) {
        Long cnt = tokenRepository.deleteByRefreshToken(token);
        return cnt;
    }


}
