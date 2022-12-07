package bungae.thunder.cakey.cake.service;

import static org.assertj.core.api.Assertions.assertThat;

import bungae.thunder.cakey.cake.domain.Cake;
import bungae.thunder.cakey.cake.repository.MemoryCakeRepository;
import bungae.thunder.cakey.user.domain.User;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

    @Test
    void createCake() {
        // given
        User user = User.builder().id(123L).build();

        Cake cake = Cake.builder().build();

        // when
        cakeService.createCake(cake, user);

        // then
        Cake result = cakeService.getCake(cake.getId()).get();
        assertThat(result.getUserId()).isEqualTo(user.getId());

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        assertThat(result.getYear()).isEqualTo(calendar.get(Calendar.YEAR));
    }

    @Test
    void getRecentCake() {
        // given
        User user = User.builder().id(123L).build();

        Cake cake1 = Cake.builder().userId(user.getId()).build();
        cakeService.createCake(cake1, user, 2021);

        Cake cake2 = Cake.builder().userId(user.getId()).build();
        cakeService.createCake(cake2, user, 2022);

        // when
        Cake result = cakeService.getRecentCake(user.getId()).get();

        // then
        assertThat(result).isEqualTo(cake2);
    }

    @Test
    void getThisYearCake() {
        // given
        User user1 = User.builder().id(123L).build();

        User user2 = User.builder().id(456L).build();

        Cake cake1 = Cake.builder().userId(user1.getId()).build();
        cakeService.createCake(cake1, user1, 2021);

        Cake cake2 = Cake.builder().userId(user1.getId()).build();
        cake2.setUserId(user1.getId());
        cakeService.createCake(cake2, user1, 2022);

        // when
        Cake result1 = cakeService.getThisYearCake(user1.getId()).get();
        Cake result2 = cakeService.getThisYearCake(user2.getId()).orElse(null);

        // then
        assertThat(result1).isEqualTo(cake2);
        assertThat(result2).isEqualTo(null);
    }

    @Test
    void getSpecificYearCake() {
        // given
        User user = User.builder().id(123L).build();

        Cake cake1 = Cake.builder().userId(user.getId()).build();
        cakeService.createCake(cake1, user, 2021);

        Cake cake2 = Cake.builder().userId(user.getId()).build();
        cakeService.createCake(cake2, user, 2022);

        // when
        Cake result1 = cakeService.getSpecificYearCake(user.getId(), 2021).get();
        Cake result2 = cakeService.getSpecificYearCake(user.getId(), 2001).orElse(null);

        // then
        assertThat(result1).isEqualTo(cake1);
        assertThat(result2).isEqualTo(null);
    }

    @Test
    void getAllCakes() {
        // given
        User user = User.builder().id(123L).build();

        Cake cake1 = Cake.builder().userId(user.getId()).build();
        cakeService.createCake(cake1, user, 2021);

        Cake cake2 = Cake.builder().userId(user.getId()).build();
        cakeService.createCake(cake2, user, 2022);

        // when
        List<Cake> result = cakeService.getAllCakes(user.getId());

        // then
        assertThat(result.size()).isEqualTo(2);
    }
}
