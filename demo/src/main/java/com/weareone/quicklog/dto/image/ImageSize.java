package com.weareone.quicklog.dto.image;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ImageSize {
    SMALL("256x256"),
    MEDIUM("512x512"),
    LARGE("1024x1024");

    private final String size;
}
