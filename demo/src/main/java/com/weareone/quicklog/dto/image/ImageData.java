package com.weareone.quicklog.dto.image;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
public class ImageData {
    private String url;

    @JsonProperty("b64_json")
    private String b64Json;
}
