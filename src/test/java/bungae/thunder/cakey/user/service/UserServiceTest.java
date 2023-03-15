package bungae.thunder.cakey.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import bungae.thunder.cakey.user.domain.User;
import bungae.thunder.cakey.user.repository.UserJpaRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UserServiceTest {
    @Mock private UserJpaRepository userRepository;

    @InjectMocks private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("유저 등록 - 성공")
    @Test
    void createUserTest() {
        // given
        User user =
                User.builder()
                        .email("test@test.com")
                        .name("테스트")
                        .birthday(LocalDate.of(2000, 1, 1))
                        .build();
        User savedUser =
                User.builder()
                        .email("test@test.com")
                        .name("테스트")
                        .birthday(LocalDate.of(2000, 1, 1))
                        .build();
        when(userRepository.save(any())).thenReturn(savedUser);

        // when
        User createdUser = userService.createUser(user);

        // then
        assertThat(createdUser.getEmail()).isEqualTo("test@test.com");
        assertThat(createdUser.getName()).isEqualTo("테스트");
        assertThat(createdUser.getBirthday()).isEqualTo(LocalDate.of(2000, 1, 1));
    }

    @DisplayName("전체 유저 조회 - 성공")
    @Test
    void getAllUsersTest() {
        // given
        List<User> userList = new ArrayList<>();
        userList.add(
                User.builder()
                        .email("test@test.com")
                        .name("테스트1")
                        .birthday(LocalDate.of(2000, 1, 1))
                        .build());
        userList.add(
                User.builder()
                        .email("test2@test.com")
                        .name("테스트2")
                        .birthday(LocalDate.of(2001, 1, 1))
                        .build());
        when(userRepository.findAll()).thenReturn(userList);

        // when
        List<User> allUsers = userService.getAllUsers();

        // then
        assertThat(allUsers.size()).isEqualTo(2);
        assertThat(allUsers.get(0).getEmail()).isEqualTo("test@test.com");
        assertThat(allUsers.get(0).getName()).isEqualTo("테스트1");
        assertThat(allUsers.get(0).getBirthday()).isEqualTo(LocalDate.of(2000, 1, 1));
    }
}
