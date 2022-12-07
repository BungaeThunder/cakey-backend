package bungae.thunder.cakey.cake.service;

import static org.assertj.core.api.Assertions.assertThat;

import bungae.thunder.cakey.cake.domain.Cake;
import bungae.thunder.cakey.cake.repository.MemoryCakeRepository;
import bungae.thunder.cakey.user.domain.User;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CakeServiceTest {

    CakeService cakeService;
    MemoryCakeRepository cakeRepository;

    @BeforeEach
    void beforeEach() {
        cakeRepository = new MemoryCakeRepository();
        cakeService = new CakeService(cakeRepository);
    }

    @AfterEach
    void afterEach() {
        cakeRepository.clearStore();
    }

    private User getDb() {
        return User.builder().id(123L).birthday(LocalDate.parse("1995-12-31")).build();
    }

    @Test
    void createCake() {
        // given
        User user = getDb();
        Cake cake = Cake.builder().build();

        // when
        cake.setId(cakeService.createCake(user));

        // then
        Cake result = cakeService.getCake(cake.getId());
        assertThat(result.getUserId()).isEqualTo(user.getId());

        LocalDate today = LocalDate.now();
        assertThat(today.getYear()).isEqualTo(result.getYear());
    }

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
}
