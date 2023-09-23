package bungae.thunder.cakey.cake.dto;

import bungae.thunder.cakey.cake.domain.Cake;
import bungae.thunder.cakey.letter.domain.Letter;
import bungae.thunder.cakey.user.domain.User;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@Getter
public class CakeByGuestResponseDto {
    private Long cakeId;
    private LocalDate birthday;
    private OwnerResponse owner;
    private WriteLetterResponse writeLetter;

    public CakeByGuestResponseDto(Cake cake, User owner, Letter letter){
        this.cakeId = cake.getId();
        this.birthday = owner.getBirthday().withYear(cake.getYear());
        this.owner = new OwnerResponse(owner);
        if (letter != null) {
            this.writeLetter = new WriteLetterResponse(letter);
        }

    }

    @Getter
    private class OwnerResponse{
        private long id;
        private String name;

        public OwnerResponse(User owner){
            this.id = owner.getId();
            this.name = owner.getName();
        }
    }

    @Getter
    private class WriteLetterResponse{
        private long id;
        private String contents;
        private String auidoUrl;
        private String reply;

        public WriteLetterResponse(Letter writeLetter){
            this.id = writeLetter.getId();
            this.contents = writeLetter.getContents();
            this.auidoUrl = writeLetter.getAudioUrl();
            this.reply = writeLetter.getReply();
        }
    }


}
