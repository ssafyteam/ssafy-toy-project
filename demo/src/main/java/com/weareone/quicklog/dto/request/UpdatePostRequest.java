package com.weareone.quicklog.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdatePostRequest {
    private String category;
    private String title;
    private String[] tags;
    private String contents;
    private boolean isPublic;
}