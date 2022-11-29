package bungae.thunder.cakey.service;

import bungae.thunder.cakey.domain.User;
import bungae.thunder.cakey.repository.MemoryUserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class UserServiceTest {
    UserService userService;
    MemoryUserRepository userRepository;

    @BeforeEach
    public void beforeEach() {
        userRepository = new MemoryUserRepository();
        userService = new UserService(userRepository);
    }

    @AfterEach
    public void afterEach() {
        userRepository.clearStore();
    }

    @Test
    void createUser() {
        // given
        User user = User.builder().name("hello").build();

        // when
        Long saveId = userService.createUser(user);

        // then
        User findUser = userRepository.findById(saveId).get();
        assertThat(user.getName()).isEqualTo(findUser.getName());
    }

    @Test
    void getAllUsers() {
        // given
        User user1 = User.builder().id(123L).build();
        userRepository.save(user1);
        User user2 = User.builder().id(124L).build();
        userRepository.save(user2);
        User user3 = User.builder().id(125L).build();
        userRepository.save(user3);

        // when
        List<User> userList = userService.getAllUsers();

        // then
        assertThat(userList.size()).isEqualTo(3);
    }

    @Test
    void getUser() {
        // given
        User user1 = User.builder().id(123L).build();
        userRepository.save(user1);

        // when
        User result = userService.getUser(user1.getId()).get();

        // then
        assertThat(result.getId()).isEqualTo(user1.getId());
    }
}