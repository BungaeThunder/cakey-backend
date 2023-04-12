package bungae.thunder.cakey.user.dto;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponseDto {

    private String email;
    private String name;
    private LocalDate birthday;
}
