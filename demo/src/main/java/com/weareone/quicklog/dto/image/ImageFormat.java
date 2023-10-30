package com.weareone.quicklog.dto.image;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ImageFormat {
    URL("url"),
    BASE64("b64_json");

    private final String format;
}
