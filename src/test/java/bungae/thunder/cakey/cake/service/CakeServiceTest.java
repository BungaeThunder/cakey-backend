package bungae.thunder.cakey.cake.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import bungae.thunder.cakey.cake.domain.Cake;
import bungae.thunder.cakey.cake.repository.CakeJpaRepository;
import bungae.thunder.cakey.user.domain.User;
import bungae.thunder.cakey.user.service.UserService;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CakeServiceTest {

    @InjectMocks
    private CakeService cakeService;
    @Mock
    CakeJpaRepository mockedCakeRepository;

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCake() {
        // given
        User user = getUser1();
        Cake cake = getCake1();

        // when
        when(mockedCakeRepository.save(any())).thenReturn(cake);

        Cake result = cakeService.createCake(user);

        // then
        assertThat(result).isEqualTo(cake);
    }

    @Test
    void getCake() {
        // given
        Cake cake = getCake1();

        // when
        when(mockedCakeRepository.findById(any())).thenReturn(Optional.ofNullable(cake));

        Cake result = cakeService.getCake(cake.getId());

        // then
        assertThat(result).isEqualTo(cake);
    }


    @Test
    void getRecentCake() {
        // given
        User user = getUser1();
        Cake cake = getCake2();

        // when
        when(mockedCakeRepository.findRecentByUserId(user.getId())).thenReturn(cake);

        Cake result = cakeService.getRecentCake(user.getId());

        // then
        assertThat(result).isEqualTo(cake);
    }


    @Test
    void getSpecificYearCake() {
        // given
        User user = getUser1();
        Cake cake = getCake1();

        // when
        when(mockedCakeRepository.findSpecificByUserIdAndYear(user.getId(),
            cake.getYear())).thenReturn(cake);

        Cake result = cakeService.getSpecificYearCake(user.getId(), getCake1().getYear());

        // then
        assertThat(result).isEqualTo(cake);
    }


    @Test
    void getAllCakes() {
        // given
        User user = getUser1();
        List<Cake> cakes = getCakeList();

        // when
        when(mockedCakeRepository.findAllByUserId(any())).thenReturn(cakes);
        List<Cake> results = cakeService.getAllCakes(user.getId());

        // then
        assertThat(results.size()).isEqualTo(2);
        assertThat(results).isEqualTo(cakes);
    }

    private User getUser1() {
        return User.builder().name("RM").birthday(LocalDate.parse("1994-09-12")).build();
    }

    private Cake getCake1() {
        return Cake.builder().user(getUser1()).year(1994).build();
    }

    private Cake getCake2() {
        return Cake.builder().user(getUser1()).year(2023).build();
    }

    private List<Cake> getCakeList() {
        return Arrays.asList(getCake1(), getCake2());
    }

}
