package bungae.thunder.cakey.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignUpWithNaverResponseDto extends UserResponseDto {
    private String token;
}
