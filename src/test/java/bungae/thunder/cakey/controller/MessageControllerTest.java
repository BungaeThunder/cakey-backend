package bungae.thunder.cakey.controller;

import bungae.thunder.cakey.cake.domain.Cake;
import bungae.thunder.cakey.cake.service.CakeService;
import bungae.thunder.cakey.message.controller.MessageController;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MessageController.class)
public class MessageControllerTest {
    @MockBean
    MessageService messageService;

    @MockBean
    UserService userService;

    @MockBean
    CakeService cakeService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Should create a new message")
    public void createMessage() throws Exception {
        User user = User.builder()
                .id(0L)
                .email("cakey@cakey.com")
                .name("Cakey")
                .birthday(LocalDate.of(1995, 5, 25))
                .build();

        Cake cake = Cake.builder()
                .id(0L)
                .year(1995)
                .userId(0L)
                .build();

        given(userService.getUser(0L))
                .willReturn(Optional.ofNullable(user));
        given(cakeService.getCake(0L))
                .willReturn(Optional.ofNullable(cake));

        JSONObject newMessage = new JSONObject();
        newMessage.put("senderId", 0L);
        newMessage.put("cakeId", 0L);
        newMessage.put("contents", "Happy birthday! Have a good day.");
        newMessage.put("audioUrl", "http://audio.url");

        mvc.perform(post("/messages")
                        .content(newMessage.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string("0"))
                .andDo(print());
    }

    @Test
    @DisplayName("Should get a proper message")
    public void getMessage() throws Exception {
        Message message = Message.builder()
                .id(0L)
                .contents("Want some pizza?")
                .reply("No.")
                .audioUrl("http://pizza.hot")
                .cakeId(0L)
                .senderId(0L)
                .build();

        given(messageService.getMessage(0L))
                .willReturn(Optional.ofNullable(message));

        mvc.perform(get("/messages/{messageId}", 0L))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(objectMapper.writeValueAsString(message)))
                .andDo(print());
    }

    @Test
    @DisplayName("Should get messages by cake id.")
    public void getMessagesByCakeId() throws Exception {
        Message message0 = Message.builder().id(0L).build();
        Message message1 = Message.builder().id(1L).build();
        Message message2 = Message.builder().id(2L).build();

        List<Message> messages = new ArrayList<>();
        Collections.addAll(messages, message0, message1, message2);

        given(messageService.getAllMessagesByCakeId(0L)).willReturn(messages);

        mvc.perform(get("/messages")
                        .param("cakeId", "0"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(objectMapper.writeValueAsString(messages)))
                .andDo(print());
    }
}
