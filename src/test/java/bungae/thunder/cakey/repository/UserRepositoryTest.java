package bungae.thunder.cakey.repository;

import bungae.thunder.cakey.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setup() {
        userRepository.deleteAll();
    }

    @DisplayName("Save user test")
    @Test
    public void saveUserTest() {
        var user = User.builder()
                .email("cakey@cakey.com")
                .name("Cakey")
                .birthday(new Date(System.currentTimeMillis()))
                .build();

        var savedUser = userRepository.save(user);

        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getEmail()).isEqualTo(user.getEmail());
        assertThat(savedUser.getName()).isEqualTo(user.getName());
        assertThat(savedUser.getBirthday()).isEqualTo(user.getBirthday());
    }
}
