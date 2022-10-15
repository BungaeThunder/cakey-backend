package bungae.thunder.cakey.repository;

import bungae.thunder.cakey.domain.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

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

        Message message = new Message();
        message.setContents("contents");
        message.setReply("reply");
        message.setAudioUrl("http://audio.url");
        message.setCakeId(0L);
        message.setSenderId(0L);

        Message result = messageRepository.save(message);

        message.setId(0L);

        assertThat(result).isEqualTo(message);
    }

    @Test
    public void findOneById() {

        Message message = new Message();
        message.setId(0L);
        message.setContents("contents");
        message.setReply("reply");
        message.setAudioUrl("http://audio.url");
        message.setCakeId(0L);
        message.setSenderId(0L);

        store.put(0L, message);
        messageRepository = new MemoryMessageRepository(store);

        Optional<Message> shouldExist = messageRepository.findOneById(0L);
        Optional<Message> shouldNotExist = messageRepository.findOneById(1L);

        assertThat(shouldExist).isNotEmpty();
        assertThat(shouldNotExist).isEmpty();
    }

    @Test
    public void findAllByCakeId() {
        Message message = new Message();
        message.setContents("contents");
        message.setReply("reply");
        message.setAudioUrl("http://audio.url");
        message.setCakeId(0L);
        message.setSenderId(0L);

        Message message2 = new Message();
        message2.setContents("contents2");
        message2.setReply("reply2");
        message2.setAudioUrl("http://audio.url2");
        message2.setCakeId(0L);
        message2.setSenderId(1L);

        store.put(0L, message);
        store.put(1L, message2);
        messageRepository = new MemoryMessageRepository(store);

        List<Message> result = messageRepository.findAllByCakeId(0L);

        assertThat(result).isEqualTo(Arrays.asList(message, message2));
    }

    @Test
    public void findAllBySenderId() {
        Message message = new Message();
        message.setContents("contents");
        message.setReply("reply");
        message.setAudioUrl("http://audio.url");
        message.setCakeId(0L);
        message.setSenderId(0L);

        Message message2 = new Message();
        message2.setContents("contents2");
        message2.setReply("reply2");
        message2.setAudioUrl("http://audio.url2");
        message2.setCakeId(1L);
        message2.setSenderId(0L);

        store.put(0L, message);
        store.put(1L, message2);
        messageRepository = new MemoryMessageRepository(store);

        List<Message> result = messageRepository.findAllBySenderId(0L);

        assertThat(result).isEqualTo(Arrays.asList(message, message2));
    }

    @Test
    public void findAll() {
        Message message = new Message();
        message.setContents("contents");
        message.setReply("reply");
        message.setAudioUrl("http://audio.url");
        message.setCakeId(0L);
        message.setSenderId(0L);

        Message message2 = new Message();
        message2.setContents("contents2");
        message2.setReply("reply2");
        message2.setAudioUrl("http://audio.url2");
        message2.setCakeId(1L);
        message2.setSenderId(0L);

        Message message3 = new Message();
        message3.setContents("contents3");
        message3.setReply("reply3");
        message3.setAudioUrl("http://audio.url3");
        message3.setCakeId(0L);
        message3.setSenderId(1L);

        store.put(0L, message);
        store.put(1L, message2);
        store.put(2L, message3);
        messageRepository = new MemoryMessageRepository(store);

        List<Message> result = messageRepository.findAll();

        assertThat(result).isEqualTo(Arrays.asList(message, message2, message3));
    }

    @Test
    public void destroyAll() {
        Message message = new Message();
        message.setContents("contents");
        message.setReply("reply");
        message.setAudioUrl("http://audio.url");
        message.setCakeId(0L);
        message.setSenderId(0L);

        store.put(0L, message);
        messageRepository = new MemoryMessageRepository(store);

        messageRepository.destroyAll();

        assertThat(store).isEmpty();
    }
}
