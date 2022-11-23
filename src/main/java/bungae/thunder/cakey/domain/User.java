package bungae.thunder.cakey.domain;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Long id;
    private String email;
    private String name;
    private Date birthday;
    // TODO: createdat 클래스화

}
