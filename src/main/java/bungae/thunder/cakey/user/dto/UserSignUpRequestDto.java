package bungae.thunder.cakey.user.dto;

import java.time.LocalDate;
import lombok.Getter;

@Getter
public class UserSignUpRequestDto {
    private String email;
    private String name;
    private LocalDate birthday;
}
