package bungae.thunder.cakey.service;

import bungae.thunder.cakey.domain.Cake;
import bungae.thunder.cakey.domain.Message;
import bungae.thunder.cakey.domain.User;
import bungae.thunder.cakey.repository.MessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
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
        Message message = new Message();
        message.setId(0L);
        User user = new User();
        Cake cake = new Cake();
        when(mockedMessageRepository.save(any())).thenReturn(message);

        Long result = messageService.createMessage(message, user, cake);

        assertThat(result).isEqualTo(0L);
    }

    @Test
    public void findMessage() {
        Message message = new Message();
        message.setId(0L);
        when(mockedMessageRepository.findOneById(0L)).thenReturn(Optional.ofNullable(message));
        when(mockedMessageRepository.findOneById(1L)).thenReturn(Optional.ofNullable(null));

        Optional<Message> shouldExist = messageService.findMessage(0L);
        Optional<Message> shouldNotExist = messageService.findMessage(1L);

        assertThat(shouldExist).isEqualTo(Optional.ofNullable(message));
        assertThat(shouldNotExist).isEmpty();
    }

    @Test
    public void findAllMessages() {
        Message message = new Message();
        Message message2 = new Message();
        when(mockedMessageRepository.findAll()).thenReturn(Arrays.asList(message, message2));

        List<Message> result = messageService.findAllMessages();

        assertThat(result).isEqualTo(Arrays.asList(message, message2));
    }

    @Test
    public void findAllMessagesByCakeId() {
        Message message = new Message();
        Message message2 = new Message();
        when(mockedMessageRepository.findAllByCakeId(anyLong())).thenReturn(Arrays.asList(message, message2));

        List<Message> result = messageService.findAllMessages(0L, FindMessageOption.BY_CAKE_ID);

        assertThat(result).isEqualTo(Arrays.asList(message, message2));
        verify(mockedMessageRepository, never()).findAllBySenderId(anyLong());
    }

    @Test
    public void findAllMessagesBySenderId() {
        Message message = new Message();
        Message message2 = new Message();
        when(mockedMessageRepository.findAllBySenderId(anyLong())).thenReturn(Arrays.asList(message, message2));

        List<Message> result = messageService.findAllMessages(0L, FindMessageOption.BY_SENDER_ID);

        assertThat(result).isEqualTo(Arrays.asList(message, message2));
        verify(mockedMessageRepository, never()).findAllByCakeId(anyLong());
    }
}
