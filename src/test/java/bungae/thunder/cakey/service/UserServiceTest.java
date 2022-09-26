package bungae.thunder.cakey.service;

import bungae.thunder.cakey.domain.User;
import bungae.thunder.cakey.repository.MemoryUserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    void signUp() {
        //Given
        User user = new User();
        user.setName("hello");
        //When
        Long saveId = userService.signUp(user);
        //Then
        User findMember = userRepository.findById(saveId).get();
        assertEquals(user.getName(), findMember.getName());
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}