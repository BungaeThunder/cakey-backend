package bungae.thunder.cakey.cake.service;

import bungae.thunder.cakey.cake.domain.Cake;
import bungae.thunder.cakey.cake.exception.CakeNotFoundException;
import bungae.thunder.cakey.cake.repository.CakeJpaRepository;
import bungae.thunder.cakey.user.domain.User;
import java.time.LocalDate;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CakeService {

    private CakeJpaRepository cakeJpaRepository;

    @Autowired
    public CakeService(CakeJpaRepository cakeJpaRepository) {
        this.cakeJpaRepository = cakeJpaRepository;
    }

    /**
     * 케이크 만들기
     */
    public Cake createCake(User user) {

        Cake newCake = Cake.builder().user(user).year(calculateYear(user.getBirthday())).build();

        return cakeJpaRepository.save(newCake);
    }

    /** 케이크 가져오기 */
    public Cake getCake(Long id) {
        Cake dbCake = cakeJpaRepository.findById(id).orElse(null);
        if (Objects.isNull(dbCake)) {
            throw new CakeNotFoundException("케이크가 존재하지 않습니다");
        }
        return dbCake;
    }

    /** 유저의 최근 케이크 가져오기 */
    public Cake getRecentCake(Long userId) {

        Cake dbCake = cakeJpaRepository.findRecentByUserId(userId);
        if (Objects.isNull(dbCake)) {
            throw new CakeNotFoundException("케이크가 존재하지 않습니다.");
        }
        return dbCake;
    }

    /** 유저의 특정 케이크 가져오기 */
    public Cake getSpecificYearCake(Long userId, Integer year) {
        Cake dbCake = cakeJpaRepository.findSpecificByUserIdAndYear(userId, year);
        if (Objects.isNull(dbCake)) {
            throw new CakeNotFoundException("케이크가 존재하지 않습니다.");
        }
        return dbCake;
    }

    /** 유저의 모든 케이크 가져오기 */
    public List<Cake> getAllCakes(Long userId) {
        List<Cake> dbCakes = cakeJpaRepository.findAllByUserId(userId);
        if (dbCakes.isEmpty()) {
            throw new CakeNotFoundException("해당 유저의 케이크가 존재하지 않습니다.");
        }
        return dbCakes;
    }

    private Integer calculateYear(LocalDate userBirthday) {
        // TODO: 정확한 시간 비교를 위해서는 request time으로 계산 필요
        LocalDate today = LocalDate.now();
        LocalDate birthday = userBirthday.withYear(today.getYear());

        return today.isAfter(birthday) ? today.getYear() + 1 : today.getYear();
    }
}
