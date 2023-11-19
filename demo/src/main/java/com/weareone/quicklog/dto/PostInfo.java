package com.weareone.quicklog.dto;

import com.weareone.quicklog.entity.Image;
import com.weareone.quicklog.entity.PostTag;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostInfo {
    private String email;
    private String nickname;
    private String title;
    private String categoryName;
    private LocalDate createdAt;
    private String contents;
    private boolean isPublic;
    private List<String> images = new ArrayList<>();
    private List<String> tags = new ArrayList<>();

    public PostInfo(String email, String nickname, String title, String categoryName, LocalDate createdAt, String contents, boolean isPublic, List<Image> images, List<PostTag> postTags) {
        this.email = email;
        this.nickname = nickname;
        this.title = title;
        this.categoryName = categoryName;
        this.createdAt = createdAt;
        this.contents = contents;
        this.isPublic = isPublic;
        this.images = images.stream()
                .map(image -> new String(image.getImageUrl()))
                .collect(Collectors.toList());
        this.tags = postTags.stream()
                .map(postTag -> new String(postTag.getTag().getTagName()))
                .collect(Collectors.toList());
    }
}
