package bungae.thunder.cakey.letter.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LetterResponseDto {
    private Long id;
    private String contents;
    private String reply;
    private String audioUrl;
    private Long cakeId;
    private Long senderId;
}
