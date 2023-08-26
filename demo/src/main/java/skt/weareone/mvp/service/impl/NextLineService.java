package skt.weareone.mvp.service.impl;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import skt.weareone.mvp.config.ChatGptConfig;
import skt.weareone.mvp.dto.request.ChatGptRequest;
import skt.weareone.mvp.dto.response.NextLine;
import skt.weareone.mvp.dto.request.SuggestionRequest;
import skt.weareone.mvp.exception.GptCannotMakeNextLineException;

@Service
public class NextLineService {
    @Value("${gpt-apiKey}")
    private String gpt_apiKey;
    private static RestTemplate restTemplate = new RestTemplate();

    public HttpEntity<ChatGptRequest> createHttpEntity(ChatGptRequest chatGptRequest) {
        HttpHeaders headers = new HttpHeaders(); // 헤더 설정
        headers.setContentType(MediaType.parseMediaType(ChatGptConfig.MEDIA_TYPE)); // Content-type JSON으로 설정
        headers.add(ChatGptConfig.AUTHORIZATION, ChatGptConfig.BEARER + gpt_apiKey); // Header에 인증 방식 설정
        return new HttpEntity<>(chatGptRequest, headers);
    }

    public NextLine getResponse(HttpEntity<ChatGptRequest> chatGptRequest) {
        ResponseEntity<NextLine> responseEntity = restTemplate.postForEntity(
                ChatGptConfig.URL,
                chatGptRequest,
                NextLine.class);
        if (isGptCannotResponse(responseEntity)) {
            System.out.println(responseEntity.getBody().getNextLine());
            throw new GptCannotMakeNextLineException();
        }
        return responseEntity.getBody();
    }

    public NextLine askQuestionToChatGpt(SuggestionRequest suggestionRequest) {
        return this.getResponse(
                this.createHttpEntity(
                        ChatGptRequest.builder()
                                .model(ChatGptConfig.MODEL)
                                .prompt(suggestionRequest.toString() + "한 줄로 다음 문장 추천해줘")
                                .maxTokens(ChatGptConfig.MAX_TOKEN)
                                .temperature(ChatGptConfig.TEMPERATURE)
                                .topP(ChatGptConfig.TOP_P)
                                .build()));
    }

    public boolean isGptCannotResponse(HttpEntity<NextLine> chatGptResponseEntity) {
        if (chatGptResponseEntity.getBody().getNextLine() == null || chatGptResponseEntity.getBody().getNextLine().isEmpty() ) {
            return true;
        }
        return false;
    }
}
