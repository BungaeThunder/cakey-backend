package bungae.thunder.cakey.message.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Message {

    private Long id;
    private String contents;
    private String reply;
    private String audioUrl;
    private Long cakeId;
    private Long senderId;

    @Builder
    public Message(
            Long id, String contents, String reply, String audioUrl, Long cakeId, Long senderId) {
        this.id = id;
        this.contents = contents;
        this.reply = reply;
        this.audioUrl = audioUrl;
        this.cakeId = cakeId;
        this.senderId = senderId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCakeId(Long cakeId) {
        this.cakeId = cakeId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }
}
