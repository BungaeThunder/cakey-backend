package bungae.thunder.cakey.letter.dto;

import lombok.Getter;

@Getter
public class CreateLetterRequestDto {
    private String contents;
    private String audioUrl;
    private Long senderId;
    private Long cakeId;
}
