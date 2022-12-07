package bungae.thunder.cakey.message.repository;

import bungae.thunder.cakey.message.domain.Message;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository {
    Message save(Message message);

    Message findOneById(Long id);

    List<Message> findAllByCakeId(Long cakeId);

    List<Message> findAllBySenderId(Long senderId);

    List<Message> findAll();
}
