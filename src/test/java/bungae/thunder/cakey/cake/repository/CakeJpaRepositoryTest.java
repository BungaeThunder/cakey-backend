package bungae.thunder.cakey.cake.repository;

import bungae.thunder.cakey.cake.domain.Cake;
import bungae.thunder.cakey.user.domain.User;
import bungae.thunder.cakey.user.repository.UserJpaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class CakeJpaRepositoryTest {

    @Autowired CakeJpaRepository cakeJpaRepository;

    @Autowired UserJpaRepository userJpaRepository;

    @Test
    public void testCakeSave() {
        // given
        User user1 = User.builder().name("test").build();
        userJpaRepository.save(user1);
        Cake given = cakeJpaRepository.save(Cake.builder().year(2023).user(user1).build());

        // when
        Cake find = cakeJpaRepository.findById(given.getId()).get();

        // then
        Assertions.assertThat(find.getId()).isEqualTo(given.getId());
        Assertions.assertThat(find.getUser().getId()).isEqualTo(given.getUser().getId());
    }
}
