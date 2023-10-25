package com.weareone.quicklog.dto.request;

import com.weareone.quicklog.dto.ImageDTO;
import com.weareone.quicklog.entity.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class UpdatePostRequest {
    private String category;
    private String title;
    private String[] tags;
    private String contents;
    private Image[] images; // 중간에 이미지가 삽입될텐데, 나중에 GET할 때 어떻게 위치를 기억하나? 관리방법?
    private boolean isPublic;
}