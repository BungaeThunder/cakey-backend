package bungae.thunder.cakey.user.domain;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id @GeneratedValue private Long id;
    private String email;
    private String name;
    private LocalDate birthday;
    // TODO: createdat 클래스화

    @Builder
    public User(String email, String name, LocalDate birthday) {
        this.email = email;
        this.name = name;
        this.birthday = birthday;
    }
}
