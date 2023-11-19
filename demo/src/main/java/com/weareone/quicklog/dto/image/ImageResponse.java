package com.weareone.quicklog.dto.image;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ImageResponse {
    private Date created;
    private List<ImageData> data;
}
