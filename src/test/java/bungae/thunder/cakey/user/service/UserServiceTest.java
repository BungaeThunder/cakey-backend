package bungae.thunder.cakey.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import bungae.thunder.cakey.user.domain.User;
import java.util.List;

import bungae.thunder.cakey.user.repository.UserJpaRepository;
import bungae.thunder.cakey.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserJpaRepository userJpaRepository;

    @Test
    void createUser() {
        // given
        User user = User.builder().name("hello").build();

        // when
        Long saveId = userService.createUser(user);

        // then
        User findUser = userService.getUser(saveId);
        assertThat(user.getName()).isEqualTo(findUser.getName());
    }

    @Test
    void getAllUsers() {
        // given
        User user1 = User.builder().id(123L).build();
        userService.createUser(user1);
        User user2 = User.builder().id(124L).build();
        userService.createUser(user2);
        User user3 = User.builder().id(125L).build();
        userService.createUser(user3);

        // when
        List<User> userList = userService.getAllUsers();

        // then
        assertThat(userList.size()).isEqualTo(3);
    }

    @Test
    void getUser() {
        // given
        User user1 = User.builder().id(123L).build();
        userService.createUser(user1);

        // when
        User result = userService.getUser(user1.getId());

        // then
        assertThat(result.getId()).isEqualTo(user1.getId());
    }
}
