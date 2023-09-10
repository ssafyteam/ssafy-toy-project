package com.weareone.quicklog.controller;

import com.weareone.quicklog.dto.request.SuggestionRequest;
import com.weareone.quicklog.dto.response.NextLineResponse;
import com.weareone.quicklog.service.impl.NextLineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@Tag(name = "포스팅 Controller", description = "포스팅 작성 API")
@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final NextLineService nextLineService;

    @Operation(summary = "다음 문장 추천", description = "포스팅 글 작성 시, AI가 다음 문장을 추천해줍니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "추천 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @PostMapping("/nextline")
    public ResponseEntity<NextLineResponse> makeNextLine(@RequestBody SuggestionRequest suggestionRequest) {
        log.info("Chat GPT에게 다음 추천 문장 요청, 질문 내용 : {}",suggestionRequest.toPromptString());
        return new ResponseEntity<>(nextLineService.askQuestionToChatGpt(suggestionRequest),HttpStatus.CREATED);
    }

//    @Operation(summary = "Tag 추가", description = "포스팅 글 작성 시, 글의 Tag를 추가해줍니다.")
//    @ApiResponses({
//            @ApiResponse(responseCode = "201", description = "태그 추가 성공"),
//            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
//            @ApiResponse(responseCode = "500", description = "서버 오류 발생")
//    })
//    @PostMapping("/tags")
//    public ResponseEntity<String> createTags(@RequestBody String tagName) {
//        return new ResponseEntity(HttpStatus.CREATED);
//    }
//
//    @Operation(summary = "카테고리 추가", description = "포스팅 글 작성 시, 글의 카테고리를 추가해줍니다.")
//    @ApiResponses({
//            @ApiResponse(responseCode = "201", description = "카테고리 추가 성공"),
//            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
//            @ApiResponse(responseCode = "500", description = "서버 오류 발생")
//    })
//    @PostMapping("/category")
//    public ResponseEntity<String> createCategory(@RequestBody String category) {
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }

    @Operation(summary = "포스팅 글 추가", description = "포스팅 글 추가")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "포스팅 추가 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @PostMapping // TODO: String -> Dto, Service 추가
    public ResponseEntity<String> createPost(@RequestBody String post) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "글 수정", description = "포스팅 글 수정")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "글 수정 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @PutMapping("/{id}") // TODO: String -> Dto, Service 추가
    public ResponseEntity<String> updatePost(@RequestBody String post,@PathVariable(name = "id") long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "글 삭제", description = "포스팅 글 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "글 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @DeleteMapping("/{id}") // TODO: Service 추가
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // TODO: 2023-09-10 String -> dto, Service 추가
    @Operation(summary = "맞춤법 검사", description = "포스팅 글 작성 시, AI가 맞춤법 검사를 해줍니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "맞춤법 검사 완료"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @PostMapping("/spell-check")
    public ResponseEntity<String> spellCheck(@RequestBody String post) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    // TODO: 2023-09-10 String -> dto, Service 추가
    @Operation(summary = "이미지 추천", description = "포스팅 글 작성 시, AI가 이미지를 추천해줍니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "추천 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @PostMapping("/image")
    public ResponseEntity<String> makeImage(@RequestBody String name) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
