package bungae.thunder.cakey.cake.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cake {

    @Id @GeneratedValue private Long id;

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
