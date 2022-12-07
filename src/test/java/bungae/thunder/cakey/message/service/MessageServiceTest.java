package bungae.thunder.cakey.message.service;

import bungae.thunder.cakey.cake.domain.Cake;
import bungae.thunder.cakey.message.domain.Message;
import bungae.thunder.cakey.message.repository.MessageRepository;
import bungae.thunder.cakey.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class MessageServiceTest {
    MessageRepository mockedMessageRepository;
    MessageService messageService;

    @BeforeEach
    void beforeEach() {
        mockedMessageRepository = mock(MessageRepository.class);
        messageService = new MessageService(mockedMessageRepository);
    }

    @Test
    public void createMessage() {
        Message message = Message.builder().id(0L).build();
        User user = User.builder().build();
        Cake cake = Cake.builder().build();
        when(mockedMessageRepository.save(any())).thenReturn(message);

        Long result = messageService.createMessage(message, user, cake);

        assertThat(result).isEqualTo(0L);
    }

    @Test
    public void getMessage() {
        Message message = Message.builder().id(0L).build();
        when(mockedMessageRepository.findOneById(0L)).thenReturn(message);
        when(mockedMessageRepository.findOneById(1L)).thenReturn(null);

        Message shouldExist = messageService.getMessage(0L);
        Message shouldNotExist = messageService.getMessage(1L);

        assertThat(shouldExist).isEqualTo(message);
        assertThat(shouldNotExist).isEqualTo(null);
    }

    @Test
    public void getAllMessages() {
        Message message = Message.builder().build();
        Message message2 = Message.builder().build();
        when(mockedMessageRepository.findAll()).thenReturn(Arrays.asList(message, message2));

        List<Message> result = messageService.getAllMessages();

        assertThat(result).isEqualTo(Arrays.asList(message, message2));
    }

    @Test
    public void getAllMessagesByCakeId() {
        Message message = Message.builder().build();
        Message message2 = Message.builder().build();
        when(mockedMessageRepository.findAllByCakeId(anyLong())).thenReturn(Arrays.asList(message, message2));

        List<Message> result = messageService.getAllMessagesByCakeId(0L);

        assertThat(result).isEqualTo(Arrays.asList(message, message2));
        verify(mockedMessageRepository, never()).findAllBySenderId(anyLong());
    }

    @Test
    public void getAllMessagesBySenderId() {
        Message message = Message.builder().build();
        Message message2 = Message.builder().build();
        when(mockedMessageRepository.findAllBySenderId(anyLong())).thenReturn(Arrays.asList(message, message2));

        List<Message> result = messageService.getAllMessagesBySenderId(0L);

        assertThat(result).isEqualTo(Arrays.asList(message, message2));
        verify(mockedMessageRepository, never()).findAllByCakeId(anyLong());
    }
}
