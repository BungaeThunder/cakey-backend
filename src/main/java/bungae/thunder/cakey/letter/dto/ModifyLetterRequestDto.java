package bungae.thunder.cakey.letter.dto;

import lombok.Getter;

@Getter
public class ModifyLetterRequestDto {

    private Long id;
    private String contents;
    private String audioUrl;
    // 검증용 정보
    private Long cakeId;
    private Long senderId;
}
