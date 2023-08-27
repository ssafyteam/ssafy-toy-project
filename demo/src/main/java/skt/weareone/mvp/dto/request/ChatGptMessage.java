package skt.weareone.mvp.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class ChatGptMessage implements Serializable {
    private String role;
    private String content;

    public ChatGptMessage(){};
    @Builder

    public ChatGptMessage(String role, String content) {
        this.role = role;
        this.content = content;
    }
}

