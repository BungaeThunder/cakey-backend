package bungae.thunder.cakey.cake.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Cake {

    private Long id;
    private Integer year;
    private Long userId;

    @Builder
    Cake(Long id, Integer year, Long userId) {
        this.id = id;
        this.year = year;
        this.userId = userId;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
