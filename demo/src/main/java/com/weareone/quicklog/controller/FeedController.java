package com.weareone.quicklog.controller;

import com.weareone.quicklog.entity.Image;
import com.weareone.quicklog.entity.Post;
import com.weareone.quicklog.entity.User;
import com.weareone.quicklog.mapper.FeedMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.weareone.quicklog.dto.response.FeedInfoResponse;
import com.weareone.quicklog.service.impl.FeedServiceImpl;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/feed")
@Tag(name = "피드    ", description = "피드 관련 api 입니다.")
public class FeedController {

    private final FeedServiceImpl feedService;
    private final FeedMapper mapper;

    @GetMapping("/mappingTest")
    public ResponseEntity mapperTest(Post post) {
        FeedInfoResponse response = mapper.postToFeedInfoResponse(post);
        System.out.println(response);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "전체 피드 조회 메서드", description = "포스트된 모든 피드들을 확인할 수 있습니다.")
    @GetMapping
    public ResponseEntity<Page<FeedInfoResponse>> findAllFeed(Pageable pageable) {
        return ResponseEntity.ok(feedService.findAll(pageable));
    }

    @Operation(summary = "검색 조회 메서드", description = "검색 조건을 달면 검색을 가능하게 합니다.")
    @GetMapping
    public ResponseEntity<Page<FeedInfoResponse>> findAllFeed(Pageable pageable, String query) {
        return ResponseEntity.ok(feedService.searchByTitle(query));
    }

    @Operation(summary = "내 피드 조회 메서드", description = "내가 등록한 피드들을 볼 수 있습니다.")
    @GetMapping("/my")
    public ResponseEntity<Page<FeedInfoResponse>> findMyFeed(Pageable pageable) {
        // TODO : 유저 정보 토큰에서 뽑아오기
        String email = null;
        return ResponseEntity.ok(feedService.findByUser(pageable, email));
    }

}
