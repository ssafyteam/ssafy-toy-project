package com.weareone.quicklog.dto.response;

import com.weareone.quicklog.dto.image.ImageData;
import com.weareone.quicklog.dto.image.ImageResponse;
import com.weareone.quicklog.entity.User;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class FeedInfoResponse {

    private Long id;
    private UserDtoResponse user;
    private String title;
    private String category;
    private LocalDate createdAt;
    private String contents;
    private List<ImageData> images;
}
