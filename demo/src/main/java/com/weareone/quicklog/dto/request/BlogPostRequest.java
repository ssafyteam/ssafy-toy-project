package com.weareone.quicklog.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;

/*
 * 게시글 작성
 * body: 카테고리, 제목, 작성날짜, 태그들, 내용 (+이미지), 공개/비공개 여부
 */
@Getter
public class BlogPostRequest {
    private String category;
    @NotEmpty(message = "제목은 공백일 수 없습니다.")
    private String title;
    private String[] tags;
    @NotEmpty(message = "내용은 공백일 수 없습니다.")
    private String contents;
    private boolean isPublic;

    @Builder
    public BlogPostRequest(String category, String title, String[] tags, String contents, boolean isPublic) {
        this.category = category;
        this.title = title;
        this.tags = tags;
        this.contents = contents;
        this.isPublic = isPublic;
    }
}