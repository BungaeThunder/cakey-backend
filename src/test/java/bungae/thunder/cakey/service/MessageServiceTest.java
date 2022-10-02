package bungae.thunder.cakey.service;

import bungae.thunder.cakey.repository.MemoryMessageRepository;
import bungae.thunder.cakey.repository.MessageRepository;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashMap;

public class MessageServiceTest {
    MessageService messageService;
    MessageRepository messageRepository;

    @BeforeEach
    void beforeEach() {
        messageRepository = new MemoryMessageRepository(new HashMap<>());
        messageService = new MessageService(messageRepository);
    }
}
