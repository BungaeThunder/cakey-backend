package bungae.thunder.cakey.repository;

import bungae.thunder.cakey.domain.Cake;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CakeRepository {
    Cake save(Cake cake);

    Optional<Cake> findById(Long id);

    Optional<Cake> findOneByUserId(Long userId);

    List<Cake> findAllByUserId(Long userId);

    Optional<Cake> findByUserIdAndYear(Long userId, Integer year);

    List<Cake> findAll();
}
