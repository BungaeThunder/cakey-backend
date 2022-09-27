package bungae.thunder.cakey.domain;

import lombok.Data;

@Data
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

}
