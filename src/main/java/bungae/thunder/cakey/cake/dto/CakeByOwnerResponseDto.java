package bungae.thunder.cakey.cake.dto;

import bungae.thunder.cakey.cake.domain.Cake;
import bungae.thunder.cakey.letter.domain.Letter;
import bungae.thunder.cakey.user.domain.User;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@ToString
@Getter
public class CakeByOwnerResponseDto {
    private Long cakeId;
    private LocalDate birthday;
    private OwnerResponse owner;
    private List<LetterResponse> letters;

    public CakeByOwnerResponseDto(Cake cake, User owner, List<Letter> letters){
        this.cakeId = cake.getId();
        this.birthday = owner.getBirthday().withYear(cake.getYear());
        this.owner = new OwnerResponse(owner);
        this.letters = letters.stream().map(LetterResponse::new).collect(Collectors.toList());

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
    private class LetterResponse{
        private long id;

        public LetterResponse(Letter letter){
            this.id = letter.getId();
        }
    }


}
