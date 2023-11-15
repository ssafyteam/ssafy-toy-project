package com.weareone.quicklog.controller;

import com.weareone.quicklog.dto.JwtToken;
import com.weareone.quicklog.dto.request.UserSignUpDto;
import com.weareone.quicklog.security.JwtTokenProvider;
import com.weareone.quicklog.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import com.weareone.quicklog.dto.request.LoginRequest;
import com.weareone.quicklog.dto.request.UserInfoRequest;
import com.weareone.quicklog.dto.response.LoginResponse;
import com.weareone.quicklog.dto.response.UserDtoResponse;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/users")
@Tag(name = "유저    ", description = "유저 관리 api 입니다.")
public class UserController {
    private UserService userService;
    private JwtTokenProvider jwtTokenProvider;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 회원가입
     * @param userDtoRequest
     * @return
     */
    @PostMapping("/signup")
    @Operation(summary = "회원 가입 메서드", description = "회원 가입 진행 요청을 받는 메서드 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successfully created"),
            @ApiResponse(responseCode = "400", description = "bad request operation")
    })
    public ResponseEntity<String> signUp(@Valid @RequestBody UserSignUpDto userSignUpDto) throws Exception {
        String response = userService.signup(userSignUpDto);
        log.info("Response: {}", response);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    /**
     * 로그인
     * @param loginRequest
     * @return Login Response
     */
    @PostMapping("/login")
    @Operation(summary = "로그인 메서드", description = "로그인 요청을 받는 메서드 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful"),
            @ApiResponse(responseCode = "400", description = "bad request operation")
    })
    public ResponseEntity<JwtToken> login(@Valid @RequestBody LoginRequest loginRequest) {
        JwtToken jwtToken = userService.login(loginRequest);
        log.info("jwtToken accessToken = {}, refreshToken = {}", jwtToken.getAccessToken(), jwtToken.getRefreshToken());
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken.getAccessToken())
                .header("Refresh-Token", "Bearer " + jwtToken.getRefreshToken())
                .build();
    }
    @GetMapping("/profile")
    @Operation(summary = "내 정보 메서드", description = "내 정보 보기 요청을 받는 메서드 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful"),
            @ApiResponse(responseCode = "400", description = "bad request operation")
    })
    public ResponseEntity<UserDtoResponse> getUserInfo(@RequestHeader("Authorization") String token){
        UserDtoResponse userDtoResponse = userService.getUser(token);
        return new ResponseEntity<>(userDtoResponse,HttpStatus.OK);
    }
    /**
     * 로그아웃
     * @param token
     * @return
     */
    @PostMapping("/logout")
    @Operation(summary = "로그아웃 메서드", description = "로그아웃 요청을 받는 메서드 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful"),
            @ApiResponse(responseCode = "400", description = "bad request operation")
    })
    public ResponseEntity<String> logout(@RequestHeader("Refresh-Token") String token) {
        Long response = userService.deleteRefreshToken(token);
        if (response > 0) {
            return new ResponseEntity<>("로그아웃 성공",HttpStatus.OK);
        } else {
            return new ResponseEntity<>("로그아웃 실패",HttpStatus.NO_CONTENT);
        }

    }

    /**
     * 회원 탈퇴
     * @param token
     * @return
     */
    @DeleteMapping
    @Operation(summary = "회원탈퇴 메서드", description = "회원탈퇴 요청을 받는 메서드 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "400", description = "bad request operation")
    })
    public ResponseEntity<String> byeUser(@RequestHeader("Authorization") String token, @RequestHeader("Refresh-Token") String refreshToken) {
        //회원정보와 연관된 모든 정보 삭제
        Long response = userService.deleteUser(token);
        Long tokenResponse = userService.deleteRefreshToken(refreshToken);
        if (response > 0 && tokenResponse > 0) {
            return new ResponseEntity("회원탈퇴 성공",HttpStatus.OK);
        } else {
            return new ResponseEntity("회원탈퇴 실패",HttpStatus.NO_CONTENT);
        }

    }

    /**
     * 내 정보 수정
     * @param token
     * @param userInfoRequest
     * @return userInfoResponse
     */
    @PutMapping
    @Operation(summary = "내 정보 수정 요청", description = "정보 수정")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully Created"),
            @ApiResponse(responseCode = "400", description = "bad request operation")
    })
    public ResponseEntity<Void> getMemberInfo(@RequestHeader("Authorization") String token,
                                                         @RequestBody UserInfoRequest userInfoRequest) throws Exception {
        userService.updateUser(token,userInfoRequest);

        return new ResponseEntity(HttpStatus.CREATED);
    }
}
