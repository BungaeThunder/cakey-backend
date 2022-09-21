package bungae.thunder.cakey.domain;

import lombok.Data;

@Data
public class Message {
    private Long id;
    private String contents;
    private String reply;
    private String audioUrl;
    private Long cakeId;
    private Long senderId;
}
