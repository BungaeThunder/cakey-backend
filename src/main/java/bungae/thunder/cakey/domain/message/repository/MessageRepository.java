package bungae.thunder.cakey.domain.message.repository;

import bungae.thunder.cakey.domain.message.domain.Message;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository {
    Message save(Message message);
    Optional<Message> findOneById(Long id);
    List<Message> findAllByCakeId(Long cakeId);
    List<Message> findAllBySenderId(Long senderId);
    List<Message> findAll();
}