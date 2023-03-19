package bungae.thunder.cakey.cake.domain;

import bungae.thunder.cakey.user.domain.User;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cake {

    @Id
    @GeneratedValue
    private Long id;

    private Integer year;

    @ManyToOne
    private User user;

    @Builder
    Cake(Integer year, User user) {
        this.year = year;
        this.user = user;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
