package bungae.thunder.cakey.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class User {
    private Long id;
    private String email;
    private String name;
    private Date birthday;
    // TODO: createdat 클래스화

}
