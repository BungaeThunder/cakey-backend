package bungae.thunder.cakey.cake.dto;

import lombok.Builder;
import lombok.Getter;

/**
 * 케이크 response dto
 */
@Builder
public class CakeResponseDto {

    public Long id;

    public Integer year;

    public Long userId;
}
