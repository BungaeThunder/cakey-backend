package bungae.thunder.cakey.message.repository;

import static org.assertj.core.api.Assertions.assertThat;

import bungae.thunder.cakey.message.domain.Message;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemoryMessageRepositoryTest {
    MemoryMessageRepository messageRepository;

    Map<Long, Message> store = new HashMap<>();

    @BeforeEach
    public void beforeEach() {
        store.clear();
    }

    @Test
    public void save() {
        messageRepository = new MemoryMessageRepository(new HashMap<>());

        Message message =
            Message.builder()
                .contents("contents")
                .reply("reply")
                .audioUrl("http://audio.url")
                .cakeId(0L)
                .senderId(0L)
                .build();

        Message result = messageRepository.save(message);

        message.setId(0L);

        assertThat(result).isEqualTo(message);
    }

    @Test
    public void findOneById() {

        Message message =
            Message.builder()
                .id(0L)
                .contents("contents")
                .reply("reply")
                .audioUrl("http://audio.url")
                .cakeId(0L)
                .senderId(0L)
                .build();

        store.put(0L, message);
        messageRepository = new MemoryMessageRepository(store);

        Message shouldExist = messageRepository.findOneById(0L);
        Message shouldNotExist = messageRepository.findOneById(1L);

        assertThat(shouldExist).isNotNull();
        assertThat(shouldNotExist).isNull();
    }

    @Test
    public void findAllByCakeId() {
        Message message =
            Message.builder()
                .contents("contents")
                .reply("reply")
                .audioUrl("http://audio.url")
                .cakeId(0L)
                .senderId(0L)
                .build();

        Message message2 =
            Message.builder()
                .contents("contents2")
                .reply("reply2")
                .audioUrl("http://audio.url2")
                .cakeId(0L)
                .senderId(1L)
                .build();

        store.put(0L, message);
        store.put(1L, message2);
        messageRepository = new MemoryMessageRepository(store);

        List<Message> result = messageRepository.findAllByCakeId(0L);

        assertThat(result).isEqualTo(Arrays.asList(message, message2));
    }

    @Test
    public void findAllBySenderId() {
        Message message =
            Message.builder()
                .contents("contents")
                .reply("reply")
                .audioUrl("http://audio.url")
                .cakeId(0L)
                .senderId(0L)
                .build();

        Message message2 =
            Message.builder()
                .contents("contents2")
                .reply("reply2")
                .audioUrl("http://audio.url2")
                .cakeId(1L)
                .senderId(0L)
                .build();

        store.put(0L, message);
        store.put(1L, message2);
        messageRepository = new MemoryMessageRepository(store);

        List<Message> result = messageRepository.findAllBySenderId(0L);

        assertThat(result).isEqualTo(Arrays.asList(message, message2));
    }

    @Test
    public void findAll() {
        Message message =
            Message.builder()
                .contents("contents")
                .reply("reply")
                .audioUrl("http://audio.url")
                .cakeId(0L)
                .senderId(0L)
                .build();

        Message message2 =
            Message.builder()
                .contents("contents2")
                .reply("reply2")
                .audioUrl("http://audio.url2")
                .cakeId(1L)
                .senderId(0L)
                .build();

        Message message3 =
            Message.builder()
                .contents("contents3")
                .reply("reply3")
                .audioUrl("http://audio.url3")
                .cakeId(0L)
                .senderId(1L)
                .build();

        store.put(0L, message);
        store.put(1L, message2);
        store.put(2L, message3);
        messageRepository = new MemoryMessageRepository(store);

        List<Message> result = messageRepository.findAll();

        assertThat(result).isEqualTo(Arrays.asList(message, message2, message3));
    }

    @Test
    public void destroyAll() {
        Message message =
            Message.builder()
                .contents("contents")
                .reply("reply")
                .audioUrl("http://audio.url")
                .cakeId(0L)
                .senderId(0L)
                .build();

        store.put(0L, message);
        messageRepository = new MemoryMessageRepository(store);

        messageRepository.destroyAll();

        assertThat(store).isEmpty();
    }
}
