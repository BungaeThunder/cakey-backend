package bungae.thunder.cakey.cake.converter;

import bungae.thunder.cakey.cake.domain.Cake;
import bungae.thunder.cakey.cake.dto.CakeResponseDto;
import bungae.thunder.cakey.common.converter.CommonConverter;
import org.springframework.stereotype.Component;

/**
 * {@link Cake} to {@link CakeResponseDto}
 */
@Component
public class CakeResponseDtoConverter implements CommonConverter<Cake, CakeResponseDto> {

    @Override
    public CakeResponseDto convert(Cake source) {
        return CakeResponseDto.builder()
            .id(source.getId())
            .year(source.getYear())
            .userId(source.getUser().getId())
            .build();
    }
}
