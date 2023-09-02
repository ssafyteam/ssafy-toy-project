package skt.weareone.mvp.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import skt.weareone.mvp.config.ChatGptConfig;
import skt.weareone.mvp.dto.request.ChatGptMessage;
import skt.weareone.mvp.dto.request.ChatGptRequest;
import skt.weareone.mvp.dto.response.NextLineRes;
import skt.weareone.mvp.dto.request.SuggestionRequest;
import skt.weareone.mvp.dto.response.NextLineResponse;
import skt.weareone.mvp.exception.GptCannotMakeNextLineException;

import java.util.ArrayList;
import java.util.List;

@Service
public class NextLineService {
    @Value("${gpt-apiKey}")
    private String gpt_apiKey;
    private static RestTemplate restTemplate = new RestTemplate();

    public HttpEntity<ChatGptRequest> createHttpEntity(ChatGptRequest chatGptRequest) {
        System.out.println(gpt_apiKey);
        HttpHeaders headers = new HttpHeaders(); // 헤더 설정
        headers.setContentType(MediaType.parseMediaType(ChatGptConfig.MEDIA_TYPE)); // Content-type JSON으로 설정
        headers.add(ChatGptConfig.AUTHORIZATION, ChatGptConfig.BEARER + gpt_apiKey); // Header에 인증 방식 설정
        return new HttpEntity<>(chatGptRequest, headers);
    }

    public NextLineResponse getResponse(HttpEntity<ChatGptRequest> chatGptRequest) {

        ResponseEntity<NextLineResponse> responseEntity = restTemplate.postForEntity(
                ChatGptConfig.URL,
                chatGptRequest,
                NextLineResponse.class);
        if (isGptCannotResponse(responseEntity)) {
            throw new GptCannotMakeNextLineException();
        }
        return responseEntity.getBody();
    }

    public NextLineResponse askQuestionToChatGpt(SuggestionRequest suggestionRequest) {
        List<ChatGptMessage> messages = new ArrayList<>();
        messages.add(ChatGptMessage.builder()
                .role(ChatGptConfig.ROLE)
                .content(suggestionRequest.toPromptString())
                .build());
        return this.getResponse(
                this.createHttpEntity(
                        ChatGptRequest.builder()
                                .model(ChatGptConfig.MODEL)
//                                .prompt(suggestionRequest.toPromptString())
                                .maxTokens(ChatGptConfig.MAX_TOKEN)
                                .temperature(ChatGptConfig.TEMPERATURE)
//                                .topP(ChatGptConfig.TOP_P)
                                .stream(ChatGptConfig.STREAM)
                                .messages(messages)
                                .build()));
    }

    public boolean isGptCannotResponse(HttpEntity<NextLineResponse> chatGptResponseEntity) {
        if (chatGptResponseEntity.getBody() == null) {
            return true;
        }
        return false;
    }
}
