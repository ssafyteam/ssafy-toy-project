package com.weareone.quicklog.dto.request;

import lombok.Builder;
import lombok.Getter;
import com.weareone.quicklog.dto.ImageDTO;

import java.time.LocalDate;

/*
 * 게시글 작성
 * body: 카테고리, 제목, 작성날짜, 태그들, 내용 (+이미지), 공개/비공개 여부
 */
@Getter
public class BlogPostRequest {
    private String category;
    private String title;
    private LocalDate createdAt;
    private String[] tags;
    private String contents;
    private ImageDTO[] images; // 중간에 이미지가 삽입될텐데, 나중에 GET할 때 어떻게 위치를 기억하나? 관리방법?
    private boolean isPublic;

    @Builder
    public BlogPostRequest(String category, String title, LocalDate createdAt, String[] tags, String contents, ImageDTO[] images, boolean isPublic) {
        this.category = category;
        this.title = title;
        this.createdAt = createdAt;
        this.tags = tags;
        this.contents = contents;
        this.images = images;
        this.isPublic = isPublic;
    }


}