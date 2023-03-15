package bungae.thunder.cakey.user.controller;

import bungae.thunder.cakey.user.domain.User;
import bungae.thunder.cakey.user.exception.UserNotFoundException;
import bungae.thunder.cakey.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {
    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    private List<User> users;

    @BeforeEach
    void setUp() {
        openMocks(this);

        users = new ArrayList<>();
        users.add(User.builder()
                .email("test1@test.com")
                .name("Test1")
                .birthday(LocalDate.of(2000, 1, 1))
                .build());
        users.add(User.builder()
                .email("test2@test.com")
                .name("Test2")
                .birthday(LocalDate.of(1990, 1, 1))
                .build());
    }

    @Test
    void getAllUsers_shouldReturnAllUsers() throws Exception {
        given(userService.getAllUsers()).willReturn(users);

        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].email", equalTo("test1@test.com")))
                .andExpect(jsonPath("$[0].name", equalTo("Test1")))
                .andExpect(jsonPath("$[0].birthday", equalTo("2000-01-01")))
                .andExpect(jsonPath("$[1].email", equalTo("test2@test.com")))
                .andExpect(jsonPath("$[1].name", equalTo("Test2")))
                .andExpect(jsonPath("$[1].birthday", equalTo("1990-01-01")));
    }

    @Test
    void getUser_withValidId_shouldReturnUser() throws Exception {
        User user = users.get(0);
        given(userService.getUser(anyLong())).willReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", equalTo(user.getEmail())))
                .andExpect(jsonPath("$.name", equalTo(user.getName())))
                .andExpect(jsonPath("$.birthday", equalTo("2000-01-01")));
    }

//    @Test
//    void getUser_withInvalidId_shouldReturnNotFound() throws Exception {
//        given(userService.getUser(anyLong())).willThrow(new UserNotFoundException("User not found"));
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/users/100"))
//                .andExpect(400);
//    }

//    @Test
//    void signUpUser_shouldCreateUser() throws Exception {
//        User newUser = User.builder()
//                .email("new@test.com")
//                .name("New User")
//                .birthday(LocalDate.of(1995, 2, 15))
//                .build();
//        given(userService.createUser(newUser)).willReturn(newUser);
//
//        String json = "{ \"email\": \"new@test.com\", \"name\": \"New User\", \"birthday\": \"1995-02-15\" }";
//
//        // TODO: Mocking
//
//    }
}