package bungae.thunder.cakey.cake.repository;

import bungae.thunder.cakey.cake.domain.Cake;
import java.util.List;

public interface CakeRepository {
    Cake save(Cake cake);

    Cake findById(Long id);

    Cake findOneByUserId(Long userId);

    List<Cake> findAllByUserId(Long userId);

    Cake findByUserIdAndYear(Long userId, Integer year);

    List<Cake> findAll();
}
