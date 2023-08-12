package bungae.thunder.cakey.letter.repository;

import bungae.thunder.cakey.letter.domain.Letter;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LetterJpaRepository extends JpaRepository<Letter, Long> {
    @Query("SELECT m FROM Letter m JOIN FETCH m.cake WHERE m.cake.id = :cakeId")
    List<Letter> findAllByCakeId(Long cakeId);

    @Query("SELECT m FROM Letter m JOIN FETCH m.sender WHERE m.sender.id = :senderId")
    List<Letter> findAllBySenderId(Long senderId);

    Letter findBySenderIdAndCakeId(Long senderId, Long cakeId);
}
