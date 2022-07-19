package bungae.thunder.cakey.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.sql.Date;

@Data
@Builder
public class User {
    @Id
    private Long id;

    private String email;

    private String name;

    private Date birthday;
}
