package bungae.thunder.cakey.report.domain;

import bungae.thunder.cakey.message.domain.Message;
import javax.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@ToString(of = {"id", "message", "contents"})
public class Report {
    /** TODO: 논의 내용 신고한 사람의 userID 해당 신고 처리 결과 신고 유형 ? */
    @Id
    @GeneratedValue
    private Long id;

    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    private Message message;

    @Builder
    public Report(String contents, Message message) {
        this.contents = contents;
        this.message = message;
    }
}
