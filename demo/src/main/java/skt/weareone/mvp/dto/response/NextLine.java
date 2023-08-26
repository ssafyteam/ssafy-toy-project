package skt.weareone.mvp.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class NextLine implements Serializable {
    private String nextLine; // 다음 문장 추천

    @Builder
    public NextLine(String nextLine) {
        this.nextLine = nextLine;
    }
}
