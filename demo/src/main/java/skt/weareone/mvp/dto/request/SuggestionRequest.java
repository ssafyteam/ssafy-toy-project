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
    public SuggestionRequest(String previousText, String title, List<String> tag) {
        this.previousText = previousText;
        this.title = title;
        this.tag = tag;
    }


    public String toPromptString() {
//        return "블로그 포스팅 글의 제목 :  " +
//                title + '\'' +
//                ", 태그 : " + tag +
//                ", 포스팅 글 : " +
        return "포스팅 글 : " +
                previousText +
                "포스팅 글의 바로 이전 문장을 바탕으로 다음 문장을 1개 추천해주세요";
    }
}
