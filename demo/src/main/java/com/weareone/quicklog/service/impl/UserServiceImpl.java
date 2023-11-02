package com.weareone.quicklog.service.impl;

import com.weareone.quicklog.dto.JwtToken;
import com.weareone.quicklog.dto.request.LoginRequest;
import com.weareone.quicklog.dto.request.UserDtoRequest;
import com.weareone.quicklog.dto.response.UserDtoResponse;
import com.weareone.quicklog.entity.Token;
import com.weareone.quicklog.entity.User;
import com.weareone.quicklog.exception.BlogAPIException;
import com.weareone.quicklog.exception.ResourceNotFoundException;
import com.weareone.quicklog.repository.TokenRepository;
import com.weareone.quicklog.repository.UserRepository;
import com.weareone.quicklog.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;

//    public UserServiceImpl(UserRepository userRepository, AuthenticationManagerBuilder authenticationManagerBuilder, JwtTokenProvider jwtTokenProvider, ModelMapper mapper, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.authenticationManagerBuilder = authenticationManagerBuilder;
//        this.jwtTokenProvider = jwtTokenProvider;
//        this.mapper = mapper;
//        this.passwordEncoder = passwordEncoder;
//    }

//    @Override
//    public String signup(UserDtoRequest userDtoRequest) {
//        if(userRepository.existsByEmail(userDtoRequest.getEmail())) {
//            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"이미 가입된 이메일입니다");
//        }
//        User user = new User();
//        user.
//        System.out.println("tet");
//        user.setEmail(userDtoRequest.getEmail());
//        user.setPassword(passwordEncoder.encode(userDtoRequest.getPassword()));
//        user.setName(userDtoRequest.getName());
//        user.setNickname(userDtoRequest.getNickname());
//        user.setBirth(userDtoRequest.getBirth());
//        userRepository.save(user);
//        return "회원가입 성공";
//    }

    @Override
    public JwtToken login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));

        if (!loginRequest.getPassword().equals(user.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
//        // 1. username + password 를 기반으로 Authentication 객체 생성
//        // 이때 authentication 은 인증 여부를 확인하는 authenticated 값이 false
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
//        System.out.println(authenticationToken);
//        // 2. 실제 검증. authenticate() 메서드를 통해 요청된 User 에 대한 검증 진행
//        // authenticate 메서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드 실행
//        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
//        System.out.println("name "+authentication.getDetails());
        // 3. 인증 정보를 기반으로 JWT 토큰 생성
//        JwtToken jwtToken = jwtTokenProvider.generateToken(authentication);
        JwtToken jwtToken = jwtTokenProvider.generateToken(user.getEmail(), user.getRoles());
        System.out.println(jwtToken.getRefreshToken());
        tokenRepository.save(new Token(jwtToken.getRefreshToken()));
        return jwtToken;
    }

    @Override
    public UserDtoResponse updateUserInfo(UserDtoRequest userDtoRequest, long id) {
//        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user","id",id));
//
//        user.setEmail(userDtoRequest.getEmail());
//        user.setPassword(passwordEncoder.encode(userDtoRequest.getPassword()));
//        user.setName(userDtoRequest.getName());
//        user.setNickname(userDtoRequest.getNickname());
//        user.setBirth(userDtoRequest.getBirth());
//
//        User updatedUser = userRepository.save(user);
//        UserDtoResponse userDtoResponse = mapper.map(updatedUser, UserDtoResponse.class);
//        return userDtoResponse;
        return null;
    }
}
