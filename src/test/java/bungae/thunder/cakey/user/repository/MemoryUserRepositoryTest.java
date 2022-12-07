package bungae.thunder.cakey.user.repository;

import static org.assertj.core.api.Assertions.assertThat;

import bungae.thunder.cakey.user.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class MemoryUserRepositoryTest {
    MemoryUserRepository repository = new MemoryUserRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        // given
        User user = User.builder().name("spring").build();
        // when
        repository.save(user);
        // then
        User result = repository.findById(user.getId());
        assertThat(result).isEqualTo(user);
    }

    // TODO: 테스트 코드 고치기
    //
    //    @Test
    //    public void findAll() {
    //        //given
    //        User user1 = User.builder().name("spring1").build();
    //        repository.save(user1);
    //        User user2 = User.builder().name("spring2").build();
    //        repository.save(user2);
    //        //when
    //        List<User> result = repository.findAll();
    //        //then
    //        assertThat(result.size()).isEqualTo(2);
    //    }

}
