package bungae.thunder.cakey.message.dto;

import lombok.Getter;

@Getter
public class CreateMessageRequestDto {
    private String contents;
    private String audioUrl;
    private Long senderId;
    private Long cakeId;
}
