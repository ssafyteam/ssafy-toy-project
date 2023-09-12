package com.weareone.quicklog.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.weareone.quicklog.dto.request.ChatGptMessage;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter

public class NextLineRes implements Serializable {
//    private String text;
    private ChatGptMessage message;
    private Integer index;
    @JsonProperty("finish_reason")
    private String finishReason;
    public NextLineRes(){}
    @Builder

    public NextLineRes(ChatGptMessage message, Integer index, String finishReason) {
        this.message = message;
        this.index = index;
        this.finishReason = finishReason;
    }
    //    public NextLineRes(String text, Integer index, String finishReason) {
//        this.text = text;
//        this.index = index;
//        this.finishReason = finishReason;
//    }
}
