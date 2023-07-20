package bungae.thunder.cakey.letter.domain;

import bungae.thunder.cakey.cake.domain.Cake;
import bungae.thunder.cakey.user.domain.User;
import java.util.Date;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Letter {

    @Builder
    public Letter(
            String contents, String reply, String audioUrl, Boolean bookmark, Boolean isRead) {
        this.contents = contents;
        this.reply = reply;
        this.audioUrl = audioUrl;
        this.bookmark = bookmark;
        this.isRead = isRead;
    }

    @Id @GeneratedValue private Long id;

    private String contents;
    private String reply;
    private String audioUrl;
    private Boolean bookmark;
    private Boolean isRead;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cake cake;

    @ManyToOne(fetch = FetchType.LAZY)
    private User sender;

    public void setCake(Cake cake) {
        this.cake = cake;
    }

    public void setSender(User user) {
        this.sender = user;
    }
}
