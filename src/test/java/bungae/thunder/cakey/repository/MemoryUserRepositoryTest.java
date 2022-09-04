package bungae.thunder.cakey.repository;

import bungae.thunder.cakey.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryUserRepositoryTest {
    MemoryUserRepository repository = new MemoryUserRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        //given
        User user = new User();
        user.setName("spring");
        //when
        repository.save(user);
        //then
        User result = repository.findById(user.getId()).get();
        assertThat(result).isEqualTo(user);
    }

    @Test
    public void findByName() {
        //given
        User user1 = new User();
        user1.setName("spring1");
        repository.save(user1);
        User user2 = new User();
        user2.setName("spring2");
        repository.save(user2);
        //when
        User result = repository.findByName("spring1").get();
        //then
        assertThat(result).isEqualTo(user1);
    }

    @Test
    public void findAll() {
        //given
        User user1 = new User();
        user1.setName("spring1");
        repository.save(user1);
        User user2 = new User();
        user2.setName("spring2");
        repository.save(user2);
        //when
        List<User> result = repository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);
    }

}
