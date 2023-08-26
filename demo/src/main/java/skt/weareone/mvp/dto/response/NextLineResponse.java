package skt.weareone.mvp.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
public class NextLineResponse {
    private String id;
    private String object;
    private LocalDate created;
    private String model;
    private List<NextLineRes> choices;

    @Builder

    public NextLineResponse(String id, String object, LocalDate created, String model, List<NextLineRes> choices) {
        this.id = id;
        this.object = object;
        this.created = created;
        this.model = model;
        this.choices = choices;
    }
}
