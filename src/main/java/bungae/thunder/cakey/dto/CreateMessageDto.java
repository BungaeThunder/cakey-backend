package bungae.thunder.cakey.dto;

import lombok.Getter;

@Getter
public class CreateMessageDto {
    private Long senderId;
    private Long cakeId;
    private String contents;
    private String audioUrl;

    public CreateMessageDto(Long senderId, Long cakeId, String contents, String audioUrl) {
        this.senderId = senderId;
        this.cakeId = cakeId;
        this.contents = contents;
        this.audioUrl = audioUrl;
    }
}
