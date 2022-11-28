package bungae.thunder.cakey.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class User {
    private Long id;
    private String email;
    private String name;
    private LocalDate birthday;
    // TODO: createdat 클래스화

}
