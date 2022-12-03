package bungae.thunder.cakey.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Report {
    /**
     * TODO: 논의 내용
     * 신고한 사람의 userID
     * 해당 신고 처리 결과
     * 신고 유형 ?
     */
    private Long id;
    private Long messageId;
    private String contents;

    @Builder
    public Report(Long id, Long messageId, String contents) {
        this.id = id;
        this.messageId = messageId;
        this.contents = contents;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
