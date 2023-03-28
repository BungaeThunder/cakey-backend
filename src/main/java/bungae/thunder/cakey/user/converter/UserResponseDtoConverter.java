package bungae.thunder.cakey.user.converter;

import bungae.thunder.cakey.common.converter.CommonConverter;
import bungae.thunder.cakey.user.domain.User;
import bungae.thunder.cakey.user.dto.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserResponseDtoConverter implements CommonConverter<User, UserResponseDto> {

    @Override
    public UserResponseDto convert(User source) {
        return UserResponseDto.builder()
                .email(source.getEmail())
                .name(source.getName())
                .birthday(source.getBirthday())
                .build();
    }
}
