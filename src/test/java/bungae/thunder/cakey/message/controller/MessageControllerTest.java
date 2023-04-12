package bungae.thunder.cakey.message.controller;

import bungae.thunder.cakey.cake.service.CakeService;
import bungae.thunder.cakey.message.service.MessageService;
import bungae.thunder.cakey.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = MessageController.class)
public class MessageControllerTest {

    @MockBean MessageService messageService;

    @MockBean UserService userService;

    @MockBean CakeService cakeService;

    @Autowired private MockMvc mvc;

    @Autowired private ObjectMapper objectMapper;

    //    @Test
    //    @DisplayName("Should create a new message")
    //    public void createMessage() throws Exception {
    //        User user =
    //                User.builder()
    //                        .email("cakey@cakey.com")
    //                        .name("Cakey")
    //                        .birthday(LocalDate.of(1995, 5, 25))
    //                        .build();
    //
    //        Cake cake = Cake.builder.id(0L).year(1995).userId(0L).build();
    //
    //        given(userService.getUser(0L)).willReturn(user);
    //        given(cakeService.getCake(0L)).willReturn(cake);
    //
    //        JSONObject newMessage = new JSONObject();
    //        newMessage.put("senderId", 0L);
    //        newMessage.put("cakeId", 0L);
    //        newMessage.put("contents", "Happy birthday! Have a good day.");
    //        newMessage.put("audioUrl", "http://audio.url");
    //
    //        mvc.perform(
    //                        post("/messages")
    //                                .content(newMessage.toString())
    //                                .contentType(MediaType.APPLICATION_JSON))
    //                .andExpect(status().isCreated())
    //                .andExpect(content().string("0"))
    //                .andDo(print());
    //    }

    //    @Test
    //    @DisplayName("Should get a proper message")
    //    public void getMessage() throws Exception {
    //        Message message =
    //                Message.builder()
    //                        .id(0L)
    //                        .contents("Want some pizza?")
    //                        .reply("No.")
    //                        .audioUrl("http://pizza.hot")
    //                        .cakeId(0L)
    //                        .senderId(0L)
    //                        .build();
    //
    //        given(messageService.getMessage(0L)).willReturn(message);
    //
    //        mvc.perform(get("/messages/{messageId}", 0L))
    //                .andExpect(status().isOk())
    //                .andExpect(content().string(objectMapper.writeValueAsString(message)))
    //                .andDo(print());
    //    }
    //
    //    @Test
    //    @DisplayName("Should get messages by cake id.")
    //    public void getMessagesByCakeId() throws Exception {
    //        Message message0 = Message.builder().id(0L).cakeId(0L).build();
    //        Message message1 = Message.builder().id(1L).cakeId(0L).build();
    //        Message message2 = Message.builder().id(2L).cakeId(0L).build();
    //
    //        List<Message> messages = new ArrayList<>();
    //        Collections.addAll(messages, message0, message1, message2);
    //
    //        given(messageService.getAllMessagesByCakeId(0L)).willReturn(messages);
    //
    //        mvc.perform(get("/messages").param("cakeId", "0"))
    //                .andExpect(status().isOk())
    //                .andExpect(content().string(objectMapper.writeValueAsString(messages)))
    //                .andDo(print());
    //    }
}
