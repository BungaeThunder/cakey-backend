package bungae.thunder.cakey.cake.service;

import bungae.thunder.cakey.cake.domain.Cake;
import bungae.thunder.cakey.cake.repository.CakeRepository;
import bungae.thunder.cakey.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class CakeService {

    private final CakeRepository cakeRepository;

    @Autowired
    public CakeService(CakeRepository cakeRepository) {
        this.cakeRepository = cakeRepository;
    }


    /**
     * 케이크 만들기
     */
    public Long createCake(User user) {
        // TODO: 정확한 시간 비교를 위해서는 request time으로 계산 필요
        LocalDate today = LocalDate.now();
        LocalDate birthday = user.getBirthday().withYear(today.getYear());

        Cake newCake = Cake.builder()
            .userId(user.getId())
            .year(today.isAfter(birthday) ? today.getYear() + 1 : today.getYear())
            .build();

        return cakeRepository.save(newCake).getId();
    }

    /**
     * 케이크 가져오기
     */
    public Optional<Cake> getCake(Long id) {
        return cakeRepository.findById(id);
    }

    /**
     * 유저의 최근 케이크 가져오기
     */
    public Optional<Cake> getRecentCake(Long userId) {
        return cakeRepository.findOneByUserId(userId);
    }

    /**
     * 유저의 특정 케이크 가져오기
     */
    public Optional<Cake> getSpecificYearCake(Long userId, Integer year) {
        return cakeRepository.findByUserIdAndYear(userId, year);
    }

    /**
     * 유저의 모든 케이크 가져오기
     */
    public List<Cake> getAllCakes(Long userId) {
        return cakeRepository.findAllByUserId(userId);
    }
}
