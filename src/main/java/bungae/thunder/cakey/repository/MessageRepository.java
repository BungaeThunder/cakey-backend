package bungae.thunder.cakey.repository;

import bungae.thunder.cakey.domain.Message;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository {

  Message save(Message message);

  Optional<Message> findOneById(Long id);

  List<Message> findAllByCakeId(Long cakeId);

  List<Message> findAllBySenderId(Long senderId);

  List<Message> findAll();
}
