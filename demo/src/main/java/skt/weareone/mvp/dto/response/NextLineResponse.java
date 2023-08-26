package skt.weareone.mvp.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import skt.weareone.mvp.dto.response.NextLine;

import java.util.List;

@Getter
@NoArgsConstructor
public class NextLineResponse {
    private List<NextLine> nextLineList;

    @Builder

    public NextLineResponse(List<NextLine> nextLineList) {
        this.nextLineList = nextLineList;
    }
}
