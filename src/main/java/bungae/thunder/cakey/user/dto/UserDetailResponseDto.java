package bungae.thunder.cakey.user.dto;

import bungae.thunder.cakey.cake.domain.Cake;
import bungae.thunder.cakey.letter.domain.Letter;
import bungae.thunder.cakey.user.domain.User;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class UserDetailResponseDto {
    private Long userId;
    private String email;
    private String name;
    private LocalDate birthday;
    private List<CakeResponse> cakes;
    private List<LetterResponse> letters;

    public UserDetailResponseDto(User user, List<Cake> cakes, List<Letter> letters) {
        this.userId = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.birthday = user.getBirthday();
        this.cakes = cakes.stream().map(CakeResponse::new).collect(Collectors.toList());
        this.letters = letters.stream().map(LetterResponse::new).collect(Collectors.toList());
    }

    @Getter
    private class CakeResponse {
        private Long id;
        private Integer year;

        public CakeResponse(Cake cake) {
            this.id = cake.getId();
            this.year = cake.getYear();
        }
    }

    @Getter
    private class LetterResponse {
        private Long id;

        public LetterResponse(Letter letter) {
            this.id = letter.getId();
        }
    }
}
