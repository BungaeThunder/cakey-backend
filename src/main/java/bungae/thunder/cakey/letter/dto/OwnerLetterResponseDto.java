package bungae.thunder.cakey.letter.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OwnerLetterResponseDto {
    private Long id;
    private String contents;
    private String reply;
    private String audioUrl;
    private Boolean bookmark;
    private Boolean isRead;
    private Long cakeId;
    private Long senderId;
}
