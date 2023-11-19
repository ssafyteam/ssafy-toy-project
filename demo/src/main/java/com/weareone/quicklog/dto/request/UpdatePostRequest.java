package com.weareone.quicklog.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdatePostRequest {
    private String category;
    @NotEmpty(message = "제목은 공백일 수 없습니다.")
    private String title;
    private String[] tags;
    @NotEmpty(message = "내용은 공백일 수 없습니다.")
    private String contents;
    private boolean isPublic;
}