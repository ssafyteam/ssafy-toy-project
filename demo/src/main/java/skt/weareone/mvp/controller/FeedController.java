package skt.weareone.mvp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import skt.weareone.mvp.dto.response.FeedInfoResponse;
import skt.weareone.mvp.service.impl.FeedServiceImpl;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/feed")
@Tag(name = "피드    ", description = "피드 관련 api 입니다.")
public class FeedController {

    private final FeedServiceImpl feedService;

    @Operation(summary = "전체 피드 조회 메서드", description = "포스트된 모든 피드들을 확인할 수 있습니다.")
    @GetMapping
    public ResponseEntity<FeedInfoResponse> findAllFeed() {
        return ResponseEntity.ok(feedService.findAllFeed());
    }

    @Operation(summary = "검색 조회 메서드", description = "검색 조건을 달면 검색을 가능하게 합니다.")
    @GetMapping
    public ResponseEntity<FeedInfoResponse> findAllFeed(String query) {
        return ResponseEntity.ok(feedService.findSearchFeed(query));
    }

    @Operation(summary = "내 피드 조회 메서드", description = "내가 등록한 피드들을 볼 수 있습니다.")
    @GetMapping
    public ResponseEntity<FeedInfoResponse> findMyFeed(@RequestBody Long id) {
        return ResponseEntity.ok(feedService.findMyFeed(id));
    }

}
