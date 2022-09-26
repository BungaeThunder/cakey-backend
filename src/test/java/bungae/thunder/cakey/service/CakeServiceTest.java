package bungae.thunder.cakey.service;

import bungae.thunder.cakey.domain.Cake;
import bungae.thunder.cakey.domain.User;
import bungae.thunder.cakey.repository.MemoryCakeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

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
    void makeCake() {
        // given
        User user = new User();
        user.setId(123L);

        Cake cake = new Cake();

        // when
        cakeService.makeCake(cake, user);

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
        User user = new User();
        user.setId(123L);

        Cake cake1 = new Cake();
        cake1.setUserId(user.getId());
        cakeService.makeCake(cake1, user, 2021);

        Cake cake2 = new Cake();
        cake2.setUserId(user.getId());
        cakeService.makeCake(cake2, user, 2022);

        // when
        Cake result = cakeService.getRecentCake(user.getId()).get();

        // then
        assertThat(result).isEqualTo(cake2);
    }

    @Test
    void getThisYearCake() {
        // given
        User user1 = new User();
        user1.setId(123L);

        User user2 = new User();
        user2.setId(456L);

        Cake cake1 = new Cake();
        cake1.setUserId(user1.getId());
        cakeService.makeCake(cake1, user1, 2021);

        Cake cake2 = new Cake();
        cake2.setUserId(user1.getId());
        cakeService.makeCake(cake2, user1, 2022);

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
        User user = new User();
        user.setId(123L);

        Cake cake1 = new Cake();
        cake1.setUserId(user.getId());
        cakeService.makeCake(cake1, user, 2021);

        Cake cake2 = new Cake();
        cake2.setUserId(user.getId());
        cakeService.makeCake(cake2, user, 2022);

        // when
        Cake result1 = cakeService.getSpecificYearCake(user.getId(), 2021).get();
        Cake result2 = cakeService.getSpecificYearCake(user.getId(), 2001).orElse(null);

        // then
        assertThat(result1).isEqualTo(cake1);
        assertThat(result2).isEqualTo(null);
    }

    @Test
    void getAllCake() {
        // given
        User user = new User();
        user.setId(123L);

        Cake cake1 = new Cake();
        cake1.setUserId(user.getId());
        cakeService.makeCake(cake1, user, 2021);

        Cake cake2 = new Cake();
        cake2.setUserId(user.getId());
        cakeService.makeCake(cake2, user, 2022);

        // when
        List<Cake> result = cakeService.getAllCake(user.getId());

        // then
        assertThat(result.size()).isEqualTo(2);
    }

}
