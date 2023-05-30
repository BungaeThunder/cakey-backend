package bungae.thunder.cakey.message.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

import bungae.thunder.cakey.cake.domain.Cake;
import bungae.thunder.cakey.cake.service.CakeService;
import bungae.thunder.cakey.message.domain.Message;
import bungae.thunder.cakey.message.exception.MessageNotFoundException;
import bungae.thunder.cakey.message.repository.MessageJpaRepository;
import bungae.thunder.cakey.user.domain.User;
import bungae.thunder.cakey.user.service.UserService;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MessageServiceTest {
    @Mock MessageJpaRepository messageJpaRepository;
    @Mock UserService userService;
    @Mock CakeService cakeService;
    MessageService messageService;

    @Captor ArgumentCaptor<Message> messageCaptor;

    @BeforeEach
    public void setup() {
        messageService = new MessageService(messageJpaRepository, userService, cakeService);
    }

    @Test
    public void createMessage() {
        // given
        User user = User.builder().build();
        Cake cake = Cake.builder().build();

        when(userService.getUser(0L)).thenReturn(user);
        when(cakeService.getCake(0L)).thenReturn(cake);

        // when
        messageService.createMessage("Valentine by Laufey", "https://youtu.be/tyKu0uZS86Q", 0L, 0L);

        // then
        verify(messageJpaRepository).save(messageCaptor.capture());
        Message message = messageCaptor.getValue();
        assertThat(message.getContents()).isEqualTo("Valentine by Laufey");
        assertThat(message.getAudioUrl()).isEqualTo("https://youtu.be/tyKu0uZS86Q");
        assertThat(message.getSender()).isEqualTo(user);
        assertThat(message.getCake()).isEqualTo(cake);
    }

    @Test
    public void getMessage() {
        // given
        Message message = Message.builder().build();
        when(messageJpaRepository.findById(0L)).thenReturn(Optional.of(message));
        when(messageJpaRepository.findById(1L)).thenReturn(Optional.empty());

        Message shouldExist = messageService.getMessage(0L);

        assertThat(shouldExist).isEqualTo(message);
        assertThatThrownBy(() -> messageService.getMessage(1L))
                .isInstanceOf(MessageNotFoundException.class);
    }

    @Test
    public void getAllMessages() {
        Message message = Message.builder().build();
        Message message2 = Message.builder().build();
        when(messageJpaRepository.findAll()).thenReturn(Arrays.asList(message, message2));

        List<Message> result = messageService.getAllMessages();

        assertThat(result).isEqualTo(Arrays.asList(message, message2));
    }

    @Test
    public void getAllMessagesByCakeId() {
        Message message = Message.builder().build();
        Message message2 = Message.builder().build();
        when(messageJpaRepository.findAllByCakeId(anyLong()))
                .thenReturn(Arrays.asList(message, message2));

        List<Message> result = messageService.getAllMessagesByCakeId(0L);

        assertThat(result).isEqualTo(Arrays.asList(message, message2));
        verify(messageJpaRepository, never()).findAllBySenderId(anyLong());
    }

    @Test
    public void getAllMessagesBySenderId() {
        Message message = Message.builder().build();
        Message message2 = Message.builder().build();
        when(messageJpaRepository.findAllBySenderId(anyLong()))
                .thenReturn(Arrays.asList(message, message2));

        List<Message> result = messageService.getAllMessagesBySenderId(0L);

        assertThat(result).isEqualTo(Arrays.asList(message, message2));
        verify(messageJpaRepository, never()).findAllByCakeId(anyLong());
    }
}
