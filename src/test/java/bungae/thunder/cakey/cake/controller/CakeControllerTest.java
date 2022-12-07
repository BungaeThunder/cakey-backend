package bungae.thunder.cakey.cake.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;

import bungae.thunder.cakey.cake.domain.Cake;
import bungae.thunder.cakey.cake.service.CakeService;
import bungae.thunder.cakey.user.domain.User;
import bungae.thunder.cakey.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = CakeController.class)
public class CakeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    CakeService cakeService;

    @MockBean
    UserService userService;

    @Test
    @DisplayName("케이크 생성 테스트")
    public void createCake() throws Exception {
        given(userService.getUser(getDbUser1().getId())).willReturn(getDbUser1());
        given(cakeService.createCake(getDbUser1())).willReturn(getDbCake1().getId());

        JSONObject body = new JSONObject();
        body.put("id", getDbUser1().getId().toString());
        body.put("name", getDbUser1().getName());
        body.put("email", getDbUser1().getEmail());
        body.put("birthday", getDbUser1().getBirthday().toString());

        mvc.perform(post("/cakes")
                .content(body.toString())
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    @DisplayName("케이크Id로 조회 테스트")
    public void getCake() throws Exception {
        given(cakeService.getCake(getDbCake1().getId())).willReturn(getDbCake1());

        mvc.perform(get("/cakes/{cakeId}", getDbCake1().getId()))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    @DisplayName("유저Id로 조회 테스트")
    public void getAllCakes() throws Exception {
        given(userService.getUser(getDbUser1().getId())).willReturn(getDbUser1());
        given(cakeService.getAllCakes(getDbUser1().getId())).willReturn(getDbAllCakesByUser1());

        mvc.perform(get("/cakes")
                .param("userId", "1"))
            .andExpect(status().isOk())
            .andDo(print());
    }

    private User getDbUser1() {
        return User.builder().id(1L).name("RM").birthday(LocalDate.parse("1994-09-12")).build();
    }

    private Cake getDbCake1() {
        return Cake.builder().id(1L).year(2022).userId(1L).build();
    }

    private Cake getDbCake2() {
        return Cake.builder().id(4L).year(2021).userId(1L).build();
    }

    private List<Cake> getDbAllCakesByUser1() {
        List<Cake> cakes = new ArrayList<>();
        cakes.add(getDbCake1());
        cakes.add(getDbCake2());
        return cakes;
    }
}
