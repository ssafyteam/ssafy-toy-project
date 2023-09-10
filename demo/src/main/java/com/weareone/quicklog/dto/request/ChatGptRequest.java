package com.weareone.quicklog.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
public class ChatGptRequest implements Serializable {
    private String model;
//    private String prompt;
    @JsonProperty("max_tokens")
    private Integer maxTokens;
    private Double temperature;
    private Boolean stream;
    private List<ChatGptMessage> messages;
//    @JsonProperty("top_p")
//    private Double topP;

    public ChatGptRequest(){}
    @Builder

    public ChatGptRequest(String model, Integer maxTokens, Double temperature, Boolean stream, List<ChatGptMessage> messages) {
        this.model = model;
        this.maxTokens = maxTokens;
        this.temperature = temperature;
        this.stream = stream;
        this.messages = messages;
    }
    //    public ChatGptRequest(String model, String prompt, Integer maxTokens, Double temperature, Double topP) {
//        this.model = model;
//        this.prompt = prompt;
//        this.maxTokens = maxTokens;
//        this.temperature = temperature;
//        this.topP = topP;
//    }
}
