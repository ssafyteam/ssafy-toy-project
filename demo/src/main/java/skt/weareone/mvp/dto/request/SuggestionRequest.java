package skt.weareone.mvp.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

// Gpt 서버로 어떤 내용을 보낼지에 관한 RequestDto
@Getter
public class SuggestionRequest {

    private String previousText; // 현재까지 쓴 포스팅 글
    private String title; // 글 제목
    private List<String> tag; // 글 태그

    @Builder
    public SuggestionRequest(String previousText, String title, List<String> tag, String nextLine) {
        this.previousText = previousText;
        this.title = title;
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "SuggestionRequest{" +
                "previousText='" + previousText + '\'' +
                ", Title='" + title + '\'' +
                ", Tag=" + tag +
                '}';
    }
}
