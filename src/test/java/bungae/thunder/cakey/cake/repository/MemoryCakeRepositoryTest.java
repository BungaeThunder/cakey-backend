package bungae.thunder.cakey.cake.repository;

import bungae.thunder.cakey.cake.domain.Cake;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryCakeRepositoryTest {
    MemoryCakeRepository cakeRepository = new MemoryCakeRepository();

    @AfterEach
    public void afterEach() {
        cakeRepository.clearStore();
    }

    @Test
    public void save() {
        // given
        Cake cake = Cake.builder()
                .userId(123L)
                .year(2022)
                .build();

        // when
        cakeRepository.save(cake);

        // then
        Cake result = cakeRepository.findById(cake.getId());
        assertThat(result).isEqualTo(cake);
    }

    @Test
    public void findOneByUserId() {
        // given
        Cake cake1 = Cake.builder()
                .userId(123L)
                .year(2021)
                .build();
        cakeRepository.save(cake1);

        Cake cake2 = Cake.builder()
                .userId(123L)
                .year(2022)
                .build();
        cakeRepository.save(cake2);

        Cake cake3 = Cake.builder()
            .userId(456L)
            .year(2019)
            .build();
        cakeRepository.save(cake3);

        // when
        Cake result1 = cakeRepository.findOneByUserId(123L);
        Cake result2 = cakeRepository.findOneByUserId(456L);

        // then
        assertThat(result1.getYear()).isEqualTo(cake2.getYear());
        assertThat(result2.getYear()).isEqualTo(cake3.getYear());
    }

    @Test
    public void findAllByUserId() {
        // given
        Cake cake1 = Cake.builder()
                .userId(123L)
                .year(2022)
                .build();
        cakeRepository.save(cake1);

        Cake cake2 = Cake.builder()
                .userId(123L)
                .year(2021)
                .build();
        cakeRepository.save(cake2);

        Cake cake3 = Cake.builder()
                .userId(456L)
                .year(2022)
                .build();
        cakeRepository.save(cake3);

        // when
        List<Cake> result1 = cakeRepository.findAllByUserId(123L);
        List<Cake> result2 = cakeRepository.findAllByUserId(456L);
        List<Cake> result3 = cakeRepository.findAllByUserId(789L);

        // then
        assertThat(result1.size()).isEqualTo(2);
        assertThat(result2.size()).isEqualTo(1);
        assertThat(result3.size()).isEqualTo(0);
    }

    @Test
    public void findByUserIdAndYear() {
        // given
        Cake cake1 = Cake.builder()
                .userId(123L)
                .year(2022)
                .build();
        cakeRepository.save(cake1);

        Cake cake2 = Cake.builder()
            .userId(123L)
            .year(2021)
            .build();
        cakeRepository.save(cake2);

        // when
        Cake result1 = cakeRepository.findByUserIdAndYear(123L, 2021);
        Cake result2 = cakeRepository.findByUserIdAndYear(123L, 2019);

        // then
        assertThat(result1).isEqualTo(cake2);
        assertThat(result2).isEqualTo(null);
    }

    @Test
    public void findAll() {
        // given
        Cake cake1 = Cake.builder()
                .userId(123L)
                .year(2022)
                .build();
        cakeRepository.save(cake1);

        Cake cake2 = Cake.builder()
                .userId(123L)
                .year(2021)
                .build();
        cakeRepository.save(cake2);

        Cake cake3 = Cake.builder()
                .userId(456L)
                .year(2022)
                .build();
        cakeRepository.save(cake3);

        // when
        List<Cake> result = cakeRepository.findAll();

        // then
        assertThat(result.size()).isEqualTo(3);
    }
}
