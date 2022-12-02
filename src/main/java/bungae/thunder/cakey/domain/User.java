package bungae.thunder.cakey.domain;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Getter
public class User {

  private Long id;
  private String email;
  private String name;
  private LocalDate birthday;
  // TODO: createdat 클래스화

  @Builder
  public User(Long id, String email, String name, LocalDate birthday) {
    this.id = id;
    this.email = email;
    this.name = name;
    this.birthday = birthday;
  }

}
