package bungae.thunder.cakey.message.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import bungae.thunder.cakey.cake.domain.Cake;
import bungae.thunder.cakey.cake.service.CakeService;
import bungae.thunder.cakey.message.converter.MessageResponseDtoConverter;
import bungae.thunder.cakey.message.domain.Message;
import bungae.thunder.cakey.message.service.MessageService;
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

@WebMvcTest(controllers = MessageController.class)
@Import(MessageResponseDtoConverter.class)
public class MessageControllerTest {
    @MockBean MessageService messageService;
    @MockBean UserService userService;
    @MockBean CakeService cakeService;

    @Autowired MessageResponseDtoConverter messageResponseDtoConverter;

    @Autowired private MockMvc mvc;
    @Autowired private ObjectMapper objectMapper;

    @Test
    @DisplayName("Should create a new message")
    public void createMessage() throws Exception {
        // given
        String contents = "Happy birthday! Have a good day.";
        String audioUrl = "http://audio.url";
        Long senderId = 0L;
        Long cakeId = 0L;

        Cake cake = mock(Cake.class);
        User user = mock(User.class);
        Message message = mock(Message.class);

        given(messageService.createMessage(contents, audioUrl, senderId, cakeId))
                .willReturn(message);
        given(message.getContents()).willReturn(contents);
        given(message.getAudioUrl()).willReturn(audioUrl);
        given(message.getCake()).willReturn(cake);
        given(cake.getId()).willReturn(cakeId);
        given(message.getSender()).willReturn(user);
        given(user.getId()).willReturn(senderId);

        JSONObject newMessage = new JSONObject();
        newMessage.put("senderId", senderId);
        newMessage.put("cakeId", cakeId);
        newMessage.put("contents", contents);
        newMessage.put("audioUrl", audioUrl);

        // when & then
        mvc.perform(
                        post("/messages")
                                .content(newMessage.toString())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(newMessage.toString()))
                .andDo(print());
    }

    @Test
    @DisplayName("Should get a proper message")
    public void getMessage() throws Exception {
        // given
        String contents = "Want some pizza?";
        String reply = "No.";
        String audioUrl = "http://pizza.hot";
        Long senderId = 0L;
        Long cakeId = 0L;

        Cake cake = mock(Cake.class);
        User user = mock(User.class);
        Message message = mock(Message.class);

        given(messageService.getMessage(0L)).willReturn(message);
        given(message.getContents()).willReturn(contents);
        given(message.getReply()).willReturn(reply);
        given(message.getAudioUrl()).willReturn(audioUrl);
        given(message.getCake()).willReturn(cake);
        given(cake.getId()).willReturn(cakeId);
        given(message.getSender()).willReturn(user);
        given(user.getId()).willReturn(senderId);

        JSONObject result = new JSONObject();
        result.put("senderId", senderId);
        result.put("cakeId", cakeId);
        result.put("contents", contents);
        result.put("audioUrl", audioUrl);

        // when & then
        mvc.perform(get("/messages/{messageId}", 0L))
                .andExpect(status().isOk())
                .andExpect(content().json(result.toString()))
                .andDo(print());
    }
}
