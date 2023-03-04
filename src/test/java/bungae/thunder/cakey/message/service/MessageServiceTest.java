package bungae.thunder.cakey.message.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import bungae.thunder.cakey.cake.domain.Cake;
import bungae.thunder.cakey.message.domain.Message;
import bungae.thunder.cakey.message.exception.MessageNotFoundException;
import bungae.thunder.cakey.message.repository.MessageRepository;
import bungae.thunder.cakey.user.domain.User;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        when(mockedMessageRepository.findById(0L)).thenReturn(Optional.of(message));
        when(mockedMessageRepository.findById(1L)).thenReturn(Optional.empty());

        Message shouldExist = messageService.getMessage(0L);

        assertThat(shouldExist).isEqualTo(message);
        assertThatThrownBy(() -> messageService.getMessage(1L))
                .isInstanceOf(MessageNotFoundException.class);
    }

    @Test
    public void getAllMessages() {
        Message message = Message.builder().build();
        Message message2 = Message.builder().build();
        when(mockedMessageRepository.findAll()).thenReturn(Arrays.asList(message, message2));

        List<Message> result = messageService.getAllMessages();

        assertThat(result).isEqualTo(Arrays.asList(message, message2));
    }

    //    @Test
    //    public void getAllMessagesByCakeId() {
    //        Message message = Message.builder().build();
    //        Message message2 = Message.builder().build();
    //        when(mockedMessageRepository.findAllByCakeId(anyLong()))
    //                .thenReturn(Arrays.asList(message, message2));
    //
    //        List<Message> result = messageService.getAllMessagesByCakeId(0L);
    //
    //        assertThat(result).isEqualTo(Arrays.asList(message, message2));
    //        verify(mockedMessageRepository, never()).findAllBySenderId(anyLong());
    //    }
    //
    //    @Test
    //    public void getAllMessagesBySenderId() {
    //        Message message = Message.builder().build();
    //        Message message2 = Message.builder().build();
    //        when(mockedMessageRepository.findAllBySenderId(anyLong()))
    //                .thenReturn(Arrays.asList(message, message2));
    //
    //        List<Message> result = messageService.getAllMessagesBySenderId(0L);
    //
    //        assertThat(result).isEqualTo(Arrays.asList(message, message2));
    //        verify(mockedMessageRepository, never()).findAllByCakeId(anyLong());
    //    }
}
