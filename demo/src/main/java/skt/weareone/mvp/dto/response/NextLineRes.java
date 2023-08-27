package skt.weareone.mvp.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import skt.weareone.mvp.dto.request.ChatGptMessage;

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
