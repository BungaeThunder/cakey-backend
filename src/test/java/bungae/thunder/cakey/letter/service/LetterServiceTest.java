package bungae.thunder.cakey.letter.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

import bungae.thunder.cakey.cake.domain.Cake;
import bungae.thunder.cakey.cake.service.CakeService;
import bungae.thunder.cakey.letter.domain.Letter;
import bungae.thunder.cakey.letter.exception.LetterNotFoundException;
import bungae.thunder.cakey.letter.repository.LetterJpaRepository;
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
public class LetterServiceTest {
    @Mock LetterJpaRepository letterJpaRepository;
    @Mock UserService userService;
    @Mock CakeService cakeService;
    LetterService letterService;

    @Captor ArgumentCaptor<Letter> letterCaptor;

    @BeforeEach
    public void setup() {
        letterService = new LetterService(letterJpaRepository, userService, cakeService);
    }

    @Test
    public void createLetter() {
        // given
        User user = User.builder().build();
        Cake cake = Cake.builder().build();

        when(userService.getUser(0L)).thenReturn(user);
        when(cakeService.getCake(0L)).thenReturn(cake);

        // when
        letterService.createLetter("Valentine by Laufey", "https://youtu.be/tyKu0uZS86Q", 0L, 0L);

        // then
        verify(letterJpaRepository).save(letterCaptor.capture());
        Letter letter = letterCaptor.getValue();
        assertThat(letter.getContents()).isEqualTo("Valentine by Laufey");
        assertThat(letter.getAudioUrl()).isEqualTo("https://youtu.be/tyKu0uZS86Q");
        assertThat(letter.getSender()).isEqualTo(user);
        assertThat(letter.getCake()).isEqualTo(cake);
    }

    @Test
    public void getLetter() {
        // given
        Letter letter = Letter.builder().build();
        when(letterJpaRepository.findById(0L)).thenReturn(Optional.of(letter));
        when(letterJpaRepository.findById(1L)).thenReturn(Optional.empty());

        Letter shouldExist = letterService.getLetter(0L);

        assertThat(shouldExist).isEqualTo(letter);
        assertThatThrownBy(() -> letterService.getLetter(1L))
                .isInstanceOf(LetterNotFoundException.class);
    }

    @Test
    public void getAllLetters() {
        Letter letter = Letter.builder().build();
        Letter letter2 = Letter.builder().build();
        when(letterJpaRepository.findAll()).thenReturn(Arrays.asList(letter, letter2));

        List<Letter> result = letterService.getAllLetters();

        assertThat(result).isEqualTo(Arrays.asList(letter, letter2));
    }

    @Test
    public void getAllLettersByCakeId() {
        Letter letter = Letter.builder().build();
        Letter letter2 = Letter.builder().build();
        when(letterJpaRepository.findAllByCakeId(anyLong()))
                .thenReturn(Arrays.asList(letter, letter2));

        List<Letter> result = letterService.getAllLettersByCakeId(0L);

        assertThat(result).isEqualTo(Arrays.asList(letter, letter2));
        verify(letterJpaRepository, never()).findAllBySenderId(anyLong());
    }

    @Test
    public void getAllLettersBySenderId() {
        Letter letter = Letter.builder().build();
        Letter letter2 = Letter.builder().build();
        when(letterJpaRepository.findAllBySenderId(anyLong()))
                .thenReturn(Arrays.asList(letter, letter2));

        List<Letter> result = letterService.getAllLettersBySenderId(0L);

        assertThat(result).isEqualTo(Arrays.asList(letter, letter2));
        verify(letterJpaRepository, never()).findAllByCakeId(anyLong());
    }
}
