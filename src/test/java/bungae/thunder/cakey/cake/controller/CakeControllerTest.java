package bungae.thunder.cakey.cake.controller;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import bungae.thunder.cakey.cake.converter.CakeResponseDtoConverter;
import bungae.thunder.cakey.cake.domain.Cake;
import bungae.thunder.cakey.cake.dto.CakeResponseDto;
import bungae.thunder.cakey.cake.service.CakeService;
import bungae.thunder.cakey.user.domain.User;
import bungae.thunder.cakey.user.service.UserService;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = CakeController.class)
public class CakeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    CakeService cakeService;

    @MockBean
    UserService userService;

    @MockBean
    CakeResponseDtoConverter cakeResponseDtoConverter;

    @Test
    @DisplayName("케이스 생성 테스트")
    public void createCake() throws Exception {
        Long userId = 1L;
        User user = getUser1();
        Cake cake = getCake1();
        CakeResponseDto cakeResponseDto = getCakeResponse1();

        when(userService.getUser(userId)).thenReturn(user);
        when(cakeService.createCake(user)).thenReturn(cake);
        when(cakeResponseDtoConverter.convert(cake)).thenReturn(cakeResponseDto);

        mvc.perform(MockMvcRequestBuilders.get("/cakes/create/{userId}", userId))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(cakeResponseDto.getId()))
            .andExpect(
                MockMvcResultMatchers.jsonPath("$.year").value(cakeResponseDto.getYear()))
            .andExpect(
                MockMvcResultMatchers.jsonPath("$.userId")
                    .value(cakeResponseDto.getUserId()))
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("케이크Id로 조회 테스트")
    public void getCake() throws Exception {
        Cake cake = getCake1();
        CakeResponseDto cakeResponseDto = getCakeResponse1();

        when(cakeService.getCake(1L)).thenReturn(cake);
        when(cakeResponseDtoConverter.convert(cake)).thenReturn(cakeResponseDto);

        mvc.perform(MockMvcRequestBuilders.get("/cakes/1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(cakeResponseDto.getId()))
            .andExpect(
                MockMvcResultMatchers.jsonPath("$.year").value(cakeResponseDto.getYear()))
            .andExpect(
                MockMvcResultMatchers.jsonPath("$.userId")
                    .value(cakeResponseDto.getUserId()))
            .andDo(print());
    }

    @Test
    @DisplayName("유저Id로 조회 테스트")
    public void getAllCakes() throws Exception {
        User user = getUser1();
        List<Cake> cakeList = getCakeList();

        when(userService.getUser(user.getId())).thenReturn(user);
        when(cakeService.getAllCakes(user.getId())).thenReturn(cakeList);

        mvc.perform(MockMvcRequestBuilders.get("/cakes?userId=1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(print());
    }

    private User getUser1() {
        return User.builder().name("RM").birthday(LocalDate.parse("1994-09-12")).build();
    }

    private Cake getCake1() {
        return Cake.builder().user(getUser1()).year(1994).build();
    }

    private CakeResponseDto getCakeResponse1() {
        return CakeResponseDto.builder().userId(1L).year(1994).build();
    }

    private List<Cake> getCakeList() {
        return Arrays.asList(getCake1());
    }
}
