package bungae.thunder.cakey.cake.dto;

import lombok.Builder;
import lombok.Getter;

/**
 * 케이크 response dto
 */
@Getter
public class CakeResponseDto {

    private Long id;

    private Integer year;

    private Long userId;
}
