package com.weareone.quicklog.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    private String accessToken;
    private String tokenType = "Bearer";
    private String refreshToken;

    private Long memberId;
    private String nickname;
    private String image;
}
