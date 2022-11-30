package bungae.thunder.cakey.dto;

import lombok.Getter;

@Getter
public class ReportDto {
    private Long messageId;
    private String contents;

    public ReportDto(Long messageId, String contents) {
        this.messageId = messageId;
        this.contents = contents;
    }
}
