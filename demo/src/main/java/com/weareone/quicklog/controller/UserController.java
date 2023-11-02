package com.weareone.quicklog.controller;

import com.weareone.quicklog.dto.JwtToken;
import com.weareone.quicklog.entity.Token;
import com.weareone.quicklog.service.impl.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.weareone.quicklog.dto.request.LoginRequest;
import com.weareone.quicklog.dto.request.UserDtoRequest;
import com.weareone.quicklog.dto.request.UserInfoRequest;
import com.weareone.quicklog.dto.response.LoginResponse;
import com.weareone.quicklog.dto.response.UserDtoResponse;

@Slf4j
@RestController
@RequestMapping("/users")
@Tag(name = "유저    ", description = "유저 관리 api 입니다.")
public class UserController {
    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 회원가입
     * @param userDtoRequest
     * @return
     */
//    @PostMapping("/signup")
//    @Operation(summary = "회원 가입 메서드", description = "회원 가입 진행 요청을 받는 메서드 입니다.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "201", description = "successfully created"),
//            @ApiResponse(responseCode = "400", description = "bad request operation")
//    })
//    public ResponseEntity<String> signUp(@Valid @RequestBody UserDtoRequest userDtoRequest) {
//        System.out.println("user");
//        String response = userService.signup(userDtoRequest);
//        log.info("Response: {}", response);        /* 추후 작성 예정 */
//        return new ResponseEntity<>(response,HttpStatus.CREATED);
//    }

    /**
     * 로그인
     * @param loginRequest
     * @return Login Response
     */
    @PostMapping("/login")
    @Operation(summary = "로그인 메서드", description = "로그인 요청을 받는 메서드 입니다.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "successful"),
//            @ApiResponse(responseCode = "400", description = "bad request operation")
//    })
    public ResponseEntity<JwtToken> login(@Valid @RequestBody LoginRequest loginRequest) {
        JwtToken jwtToken = userService.login(loginRequest);
        log.info("jwtToken accessToken = {}, refreshToken = {}", jwtToken.getAccessToken(), jwtToken.getRefreshToken());
        System.out.println(jwtToken.getAccessToken());
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken.getAccessToken())
                .header("Refresh-Token", "Bearer " + jwtToken.getRefreshToken())
                .build();
    }

    /**
     * 로그아웃
     * @param token
     * @return
     */
    @DeleteMapping("/logout")
    @Operation(summary = "로그아웃 메서드", description = "로그아웃 요청을 받는 메서드 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful"),
            @ApiResponse(responseCode = "400", description = "bad request operation")
    })
    public ResponseEntity logout(@RequestHeader("Authorization") String token) {

        return new ResponseEntity(HttpStatus.OK);
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
    public ResponseEntity byeUser(@RequestHeader("Authorization") String token) {
        //회원정보와 연관된 모든 정보 삭제
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    
    // 인증
    /**
     * Refresh Token으로 Access Token 발급
     * @param refreshToken
     * @return LoginResponse
     */
    @PostMapping("/refresh")
    @Operation(summary = "토큰 재발급 요청", description = "Refresh Token으로 Access Token 새로 발급")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully Created"),
            @ApiResponse(responseCode = "400", description = "bad request operation")
    })
    public ResponseEntity<LoginResponse> requestRefresh(@RequestBody @NotEmpty String refreshToken) {
        LoginResponse response = new LoginResponse(); // 추후 로직 추가
        return new ResponseEntity(response, HttpStatus.CREATED);
    }


    /**
     * 내 정보 수정
     * @param token
     * @param userInfoRequest
     * @return userInfoResponse
     */
//    @PutMapping("/users/info")
    @PutMapping("/{id}")
    @Operation(summary = "내 정보 수정 요청", description = "정보 수정")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully Created"),
            @ApiResponse(responseCode = "400", description = "bad request operation")
    })
    public ResponseEntity<UserDtoResponse> getMemberInfo(@RequestHeader("Authorization") String token,
                                                         @RequestBody UserInfoRequest userInfoRequest) {
        UserDtoResponse userDtoResponse = new UserDtoResponse(); // 정보 수정 로직 추가
        return new ResponseEntity(userDtoResponse, HttpStatus.CREATED);
    }
}
