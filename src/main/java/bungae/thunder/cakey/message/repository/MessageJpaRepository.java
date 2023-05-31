package bungae.thunder.cakey.message.repository;

import bungae.thunder.cakey.message.domain.Message;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageJpaRepository extends JpaRepository<Message, Long> {
    @Query("SELECT m FROM Message m JOIN FETCH m.cake WHERE m.cake.id = :cakeId")
    List<Message> findAllByCakeId(Long cakeId);

    @Query("SELECT m FROM Message m JOIN FETCH m.sender WHERE m.sender.id = :senderId")
    List<Message> findAllBySenderId(Long senderId);
}
