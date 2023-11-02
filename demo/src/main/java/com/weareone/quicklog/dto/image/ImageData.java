package com.weareone.quicklog.dto.image;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ImageData {
    private String imageUrl;

    @JsonProperty("b64_json")
    private String b64Json;
}
