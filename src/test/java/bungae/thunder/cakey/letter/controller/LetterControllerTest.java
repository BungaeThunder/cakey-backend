package bungae.thunder.cakey.letter.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import bungae.thunder.cakey.cake.domain.Cake;
import bungae.thunder.cakey.cake.service.CakeService;
import bungae.thunder.cakey.letter.converter.LetterResponseDtoConverter;
import bungae.thunder.cakey.letter.domain.Letter;
import bungae.thunder.cakey.letter.service.LetterService;
import bungae.thunder.cakey.user.domain.User;
import bungae.thunder.cakey.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = LetterController.class)
@Import(LetterResponseDtoConverter.class)
public class LetterControllerTest {
    @MockBean LetterService letterService;
    @MockBean UserService userService;
    @MockBean CakeService cakeService;

    @Autowired LetterResponseDtoConverter letterResponseDtoConverter;

    @Autowired private MockMvc mvc;
    @Autowired private ObjectMapper objectMapper;

    @Test
    @DisplayName("Should create a new letter")
    public void createLetter() throws Exception {
        // given
        String contents = "Happy birthday! Have a good day.";
        String audioUrl = "http://audio.url";
        Long senderId = 0L;
        Long cakeId = 0L;

        Cake cake = mock(Cake.class);
        User user = mock(User.class);
        Letter letter = mock(Letter.class);

        given(letterService.createLetter(contents, audioUrl, senderId, cakeId)).willReturn(letter);
        given(letter.getContents()).willReturn(contents);
        given(letter.getAudioUrl()).willReturn(audioUrl);
        given(letter.getCake()).willReturn(cake);
        given(cake.getId()).willReturn(cakeId);
        given(letter.getSender()).willReturn(user);
        given(user.getId()).willReturn(senderId);

        JSONObject newLetter = new JSONObject();
        newLetter.put("senderId", senderId);
        newLetter.put("cakeId", cakeId);
        newLetter.put("contents", contents);
        newLetter.put("audioUrl", audioUrl);

        // when & then
        mvc.perform(
                        post("/letters")
                                .content(newLetter.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(newLetter.toString()))
                .andDo(print());
    }

    @Test
    @DisplayName("Should get a proper letter")
    public void getLetter() throws Exception {
        // given
        String contents = "Want some pizza?";
        String reply = "No.";
        String audioUrl = "http://pizza.hot";
        Long senderId = 0L;
        Long cakeId = 0L;

        Cake cake = mock(Cake.class);
        User user = mock(User.class);
        Letter letter = mock(Letter.class);

        given(letterService.getLetter(0L)).willReturn(letter);
        given(letter.getContents()).willReturn(contents);
        given(letter.getReply()).willReturn(reply);
        given(letter.getAudioUrl()).willReturn(audioUrl);
        given(letter.getCake()).willReturn(cake);
        given(cake.getId()).willReturn(cakeId);
        given(letter.getSender()).willReturn(user);
        given(user.getId()).willReturn(senderId);

        JSONObject result = new JSONObject();
        result.put("senderId", senderId);
        result.put("cakeId", cakeId);
        result.put("contents", contents);
        result.put("audioUrl", audioUrl);

        // when & then
        mvc.perform(get("/letters/{letterId}", 0L))
                .andExpect(status().isOk())
                .andExpect(content().json(result.toString()))
                .andDo(print());
    }
}
