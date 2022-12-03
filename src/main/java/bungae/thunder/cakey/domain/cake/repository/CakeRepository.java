package bungae.thunder.cakey.domain.cake.repository;

import bungae.thunder.cakey.domain.cake.domain.Cake;

import java.util.List;
import java.util.Optional;

public interface CakeRepository {
    Cake save(Cake cake);

    Optional<Cake> findById(Long id);

    Optional<Cake> findOneByUserId(Long userId);

    List<Cake> findAllByUserId(Long userId);

    Optional<Cake> findByUserIdAndYear(Long userId, Integer year);

    List<Cake> findAll();
}
