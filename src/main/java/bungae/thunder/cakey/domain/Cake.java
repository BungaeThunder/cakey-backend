package bungae.thunder.cakey.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class Cake {
    private Long id;
    private Integer year;
    private Long userId;
}
