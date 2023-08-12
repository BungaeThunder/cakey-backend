package bungae.thunder.cakey.cake.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import bungae.thunder.cakey.cake.converter.CakeResponseDtoConverter;
import bungae.thunder.cakey.cake.domain.Cake;
import bungae.thunder.cakey.cake.dto.CakeResponseDto;
import bungae.thunder.cakey.cake.service.CakeService;
import bungae.thunder.cakey.user.domain.User;
import bungae.thunder.cakey.user.service.UserService;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

@WebMvcTest(controllers = CakeController.class)
public class CakeControllerTest {

    @Autowired private MockMvc mvc;

    @MockBean CakeService cakeService;

    @MockBean UserService userService;

    @MockBean CakeResponseDtoConverter cakeResponseDtoConverter;

    @Test
    @DisplayName("케이스 생성 테스트")
    public void createCake() throws Exception {
        Long userId = 1L;
        User user = getUser1();
        Cake cake = getCake1();
        CakeResponseDto cakeResponseDto = getCakeResponse1();

        when(userService.getUser(userId)).thenReturn(Optional.ofNullable(user));
        when(cakeService.createCake(user)).thenReturn(cake);
        when(cakeResponseDtoConverter.convert(cake)).thenReturn(cakeResponseDto);

        mvc.perform(MockMvcRequestBuilders.get("/cakes/create/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(cakeResponseDto.getId()))
                .andExpect(jsonPath("$.year").value(cakeResponseDto.getYear()))
                .andExpect(jsonPath("$.userId").value(cakeResponseDto.getUserId()))
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
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(cakeResponseDto.getId()))
                .andExpect(jsonPath("$.year").value(cakeResponseDto.getYear()))
                .andExpect(jsonPath("$.userId").value(cakeResponseDto.getUserId()))
                .andDo(print());
    }

    @Test
    @DisplayName("유저Id로 조회 테스트")
    public void getAllCakes() throws Exception {
        List<Cake> cakes = getCakeList();
        List<CakeResponseDto> cakeResponseDtos = getCakeResponseDtoList();

        when(cakeService.getAllCakes(1L)).thenReturn(cakes);
        when(cakeResponseDtoConverter.convert(cakes.get(0))).thenReturn(cakeResponseDtos.get(0));
        when(cakeResponseDtoConverter.convert(cakes.get(1))).thenReturn(cakeResponseDtos.get(1));

        mvc.perform(MockMvcRequestBuilders.get("/cakes?userId=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(cakeResponseDtos.get(0).getId()))
                .andExpect(jsonPath("$[0].year").value(cakeResponseDtos.get(0).getYear()))
                .andExpect(jsonPath("$[0].userId").value(cakeResponseDtos.get(0).getUserId()))
                .andDo(print());
    }

    private User getUser1() {
        return User.builder().name("RM").birthday(LocalDate.parse("1994-09-12")).build();
    }

    private Cake getCake1() {
        return Cake.builder().user(getUser1()).year(1994).build();
    }

    private Cake getCake2() {
        return Cake.builder().user(getUser1()).year(2023).build();
    }

    private CakeResponseDto getCakeResponse1() {
        return CakeResponseDto.builder().userId(1L).year(1994).build();
    }

    private CakeResponseDto getCakeResponse2() {
        return CakeResponseDto.builder().userId(1L).year(2023).build();
    }

    private List<Cake> getCakeList() {
        return Arrays.asList(getCake1(), getCake2());
    }

    private List<CakeResponseDto> getCakeResponseDtoList() {
        return Arrays.asList(getCakeResponse1(), getCakeResponse2());
    }
}
