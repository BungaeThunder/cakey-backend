package bungae.thunder.cakey.cake.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import bungae.thunder.cakey.cake.domain.Cake;
import bungae.thunder.cakey.cake.repository.CakeJpaRepository;
import bungae.thunder.cakey.user.domain.User;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CakeServiceTest {

    CakeService cakeService;
    CakeJpaRepository mokedCakeRepository;

    @BeforeEach
    void beforeEach() {
        mokedCakeRepository = mock(CakeJpaRepository.class);
        cakeService = new CakeService(mokedCakeRepository);
    }

    @Test
    void createCake() {
        User user = User.builder().name("rm").birthday(LocalDate.of(1994, 9, 12)).build();
        Cake expectedCake = Cake.builder().user(user).year(2023).build();
        given(mokedCakeRepository.save(any())).willReturn(expectedCake);

        Cake result = cakeService.createCake(user);

        assertThat(result).isEqualTo(expectedCake);
    }

    /*
    @Test
    void getRecentCake() {
        // given
        User user = getDb();
        cakeService.createCake(user);

        // when
        Cake result = cakeService.getRecentCake(user.getId());

        // then
        assertThat(result.getYear()).isEqualTo(LocalDate.now().getYear());
    }

    @Test
    void getSpecificYearCake() {
        // given
        User user = getDb();
        Cake cake = Cake.builder().userId(123L).year(2022).build();
        cake.setId(cakeService.createCake(user));

        // when
        Cake result = cakeService.getSpecificYearCake(user.getId(), 2022);

        // then
        assertThat(cake.getYear()).isEqualTo(result.getYear());
    }

    @Test
    void getAllCakes() {
        // given
        User user = getDb();
        cakeService.createCake(user);

        // when
        List<Cake> result = cakeService.getAllCakes(user.getId());

        // then
        assertThat(result.size()).isEqualTo(1);
    }
     */
}
