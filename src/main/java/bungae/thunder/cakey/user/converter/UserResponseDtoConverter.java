package bungae.thunder.cakey.user.converter;

import bungae.thunder.cakey.common.converter.CommonConverter;
import bungae.thunder.cakey.user.domain.User;
import bungae.thunder.cakey.user.dto.UserResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class UserResponseDtoConverter implements CommonConverter<User, UserResponseDTO> {

    @Override
    public UserResponseDTO convert(User source) {
        return UserResponseDTO.builder()
                .email(source.getEmail())
                .name(source.getName())
                .birthday(source.getBirthday())
                .build();
    }
}
