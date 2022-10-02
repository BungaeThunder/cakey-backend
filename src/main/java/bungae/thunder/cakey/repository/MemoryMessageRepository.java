package bungae.thunder.cakey.repository;

import bungae.thunder.cakey.domain.Message;

import java.util.*;
import java.util.stream.Collectors;

public class MemoryMessageRepository implements MessageRepository {
    private static Map<Long, Message> store;
    private static Long sequence = 0L;

    public MemoryMessageRepository (Map<Long, Message> store) {
        this.store = store;
    }

    @Override
    public Message save(Message message) {
        message.setId(sequence++);
        store.put(message.getId(), message);
        return message;
    }

    @Override
    public Optional<Message> findOneById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Message> findAllByCakeId(Long cakeId) {
        return store
                .values()
                .stream()
                .filter(message ->
                        message.getCakeId().equals(cakeId)
                ).collect(Collectors.toList());
    }

    @Override
    public List<Message> findAllBySenderId(Long senderId) {
        return store
                .values()
                .stream()
                .filter(message ->
                        message.getSenderId().equals(senderId)
                ).collect(Collectors.toList());
    }

    @Override
    public List<Message> findAll() {
        return new ArrayList<>(store.values());
    }

    public void destroyAll() {
        store.clear();
    }
}
