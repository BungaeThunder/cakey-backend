package bungae.thunder.cakey.message.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Message {

    @Builder
    public Message(String contents, String reply, String audioUrl) {
        this.contents = contents;
        this.reply = reply;
        this.audioUrl = audioUrl;
    }

    @Id @GeneratedValue private Long id;

    private String contents;
    private String reply;
    private String audioUrl;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    //    @ManyToOne
    //    @JoinColumn(name = "id")
    //    private Cake cake;
    //
    //    @ManyToOne
    //    @JoinColumn(name = "id")
    //    private User sender;

    //    public void setCake(Cake cake) {
    //        this.cake = cake;
    //    }
    //
    //    public void setSender(User user) {
    //        this.sender = user;
    //    }
}
