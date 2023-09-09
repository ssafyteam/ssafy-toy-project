package skt.weareone.mvp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import skt.weareone.mvp.dto.request.BlogPostRequest;
import skt.weareone.mvp.dto.request.LoginRequest;
import skt.weareone.mvp.dto.request.SignupRequest;

@Slf4j
@RequiredArgsConstructor
@RestController
@Tag(name = "유저    ", description = "유저 관리 api 입니다.")
public class UserController {

    /**
     * 회원가입
     * @param signupRequest
     * @return
     */
    @PostMapping("/signup")
    @Operation(summary = "회원 가입 메서드", description = "회원 가입 진행 요청을 받는 메서드 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successfully created"),
            @ApiResponse(responseCode = "400", description = "bad request operation")
    })
    public ResponseEntity<Void> signUp(@RequestBody @Valid SignupRequest signupRequest) {

        /* 추후 작성 예정 */
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * 로그인
     */
    @PostMapping("/login")
    @Operation(summary = "로그인 메서드", description = "로그인 요청을 받는 메서드 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful"),
            @ApiResponse(responseCode = "400", description = "bad request operation")
    })
    public ResponseEntity login(@RequestBody @Valid LoginRequest loginRequest) {
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * 인증
     */

    /**
     * 내 정보
     */
}
