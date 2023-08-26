package skt.weareone.mvp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import skt.weareone.mvp.dto.request.SuggestionRequest;
import skt.weareone.mvp.dto.response.NextLine;
import skt.weareone.mvp.service.impl.NextLineService;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/blog")
public class NextLineController {
    private final NextLineService nextLineService;
    @PostMapping("/nextline")
    public ResponseEntity<NextLine> makeNextLine(@RequestBody SuggestionRequest suggestionRequest) {
        log.info("Chat GPT에게 다음 추천 문장 요청, 질문 내용 : {}",suggestionRequest.toString());
        return ResponseEntity.ok(nextLineService.askQuestionToChatGpt(suggestionRequest));
    }
}
