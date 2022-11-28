package bungae.thunder.cakey.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Message {
    private Long id;
    private String contents;
    private String reply;
    private String audioUrl;
    private Long cakeId;
    private Long senderId;
}
