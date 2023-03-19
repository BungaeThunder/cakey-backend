package bungae.thunder.cakey.cake.repository;

import bungae.thunder.cakey.cake.domain.Cake;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CakeJpaRepository extends JpaRepository<Cake, Long> {

    /**
     * 유저의 모든 케이크 조회
     */
    List<Cake> findAllByUserId(Long userId);

    /** 유저의 최근 케이크 조회 */
    Cake findRecentByUserId(Long userId);

    /** 유저의 특정년도 케이크 조회 */
    Cake findSpecificByUserIdAndYear(Long userId, Integer year);
}
