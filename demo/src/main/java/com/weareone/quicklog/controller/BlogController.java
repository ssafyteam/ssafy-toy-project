package com.weareone.quicklog.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.weareone.quicklog.dto.request.BlogPostRequest;

@Slf4j
@RequiredArgsConstructor
@RestController
@Tag(name = "블로그    ", description = "블로그 작성 관련 api 입니다.")
public class BlogController {

    @PostMapping("/{nickName}")
    @Operation(summary = "블로그 포스트 등록 메서드", description = "한 개의 포스트를 작성 후 서버에 저장하는 메서드입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successfully created"),
            @ApiResponse(responseCode = "400", description = "bad request operation")
    })
    public ResponseEntity<Void> writeBlog(@PathVariable String nickName, @RequestBody BlogPostRequest blogPostRequest) {

        /* 추후 작성 예정 */
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // 유저의 한 블로그를 GET
    public ResponseEntity<Void> getBlog() {
        return null;
    }
}
