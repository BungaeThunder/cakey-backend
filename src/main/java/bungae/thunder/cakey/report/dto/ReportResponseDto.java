package bungae.thunder.cakey.report.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReportResponseDto {
    private long id;
    private String contents;
    private long messageId;
}
