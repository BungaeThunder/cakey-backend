package bungae.thunder.cakey.user.repository;

import static org.assertj.core.api.Assertions.assertThat;

import bungae.thunder.cakey.user.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@Transactional
public class UserJpaRepositoryTest {
    @Autowired
    UserJpaRepository userJPARepository;

    @Test
    public void save() {
        // given
        User user = User.builder().name("spring").build();
        // when
        userJPARepository.save(user);
        // then
        Optional<User> result = userJPARepository.findById(user.getId());
        assertThat(result.get()).isEqualTo(user);
    }
}
