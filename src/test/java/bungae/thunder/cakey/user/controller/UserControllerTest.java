package bungae.thunder.cakey.user.controller;

import bungae.thunder.cakey.user.domain.User;
import bungae.thunder.cakey.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// ref
// https://velog.io/@leehj8896/%EC%8A%A4%ED%94%84%EB%A7%81-%EB%B6%80%ED%8A%B8-Hello-Controller-%ED%85%8C%EC%8A%A4%ED%8A%B8-%EC%BD%94%EB%93%9C-%EC%9E%91%EC%84%B1%ED%95%98%EA%B8%B0
// https://spring.io/guides/gs/testing-web/
// https://reflectoring.io/spring-boot-web-controller-test/ (recommend)
@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    UserService userService;

    @Test
    @DisplayName("전체 유저 조회 테스트")
    public void getAllUsers() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(User.builder().id(123L).name("jinWoo").build());

        given(userService.getAllUsers()).willReturn(users);

        mvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("jinWoo")));
    }

    @Test
    @DisplayName("유저ID가 존재한 유저 조회 테스트")
    public void getUser() throws Exception {
        User user1 = User.builder().id(123L).name("jinWoo").build();

        given(userService.getUser(123L)).willReturn(user1);

        mvc.perform(get("/users/{userId}", 123L))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("jinWoo")));
    }

    // TODO: 유저 아이디 없는 경우 구현

    @Test
    @DisplayName("유저 회원 가입 테스트")
    public void signUpUser() throws Exception {
        String content = objectMapper.writeValueAsString(User.builder().id(123L).name("jinWoo").build());

        mvc.perform(post("/users/signUp")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
                //.andExpect(content().string("jinWoo")) // TODO: body가 없어서 에러 나는 중, post 방식 형식 맞추면 이 부분 ㄷ시
    }
}

