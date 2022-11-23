package bungae.thunder.cakey.service;

import bungae.thunder.cakey.domain.Cake;
import bungae.thunder.cakey.domain.User;
import bungae.thunder.cakey.repository.CakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Long makeCake(User user) {
        Calendar current = new GregorianCalendar();
        current.setTime(new Date());
        Integer currentYear = current.get(Calendar.YEAR);

        // TODO: 생일 비교

        Cake newCake = Cake.builder()
                .userId(user.getId())
                .year(currentYear)
                .build();

        return cakeRepository.save(newCake).getId();
    }

    /**
     * 케이크 만들기
     */
    @Deprecated
    public Long makeCake(Cake cake, User user) {
        cake.setUserId(user.getId());

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        cake.setYear(calendar.get(Calendar.YEAR));

        cakeRepository.save(cake);
        return cake.getId();
    }

    /**
     * 특정 케이크 만들기
     */
    @Deprecated
    public Long makeCake(Cake cake, User user, Integer year) {
        cake.setUserId(user.getId());
        cake.setYear(year);

        cakeRepository.save(cake);
        return cake.getId();
    }

    /**
     * 케이크 가져오기
     */
    public Optional<Cake> getCake(Long id) {
        return cakeRepository.findById(id);
    }

    /**
     * 유저의 최근 케이크 가져오기
     * TODO: 논의 사항
     * 해가 바뀌면 모든 유저의 케이크가 자동으로 생기는지? 아니면 케이크 만들기 액션을 해야 생기는지?
     * 20년에 가입한 유저의 케이크가 21년 없이 20년, 22년만 존재할 수 있는지
     * 회원 가입 시에는 무조건 가입년도의 케이크는 기본으로 생성
     */
    public Optional<Cake> getRecentCake(Long userId) {
        return cakeRepository.findOneByUserId(userId);
    }

    /**
     * 유저의 올해 케이크 가져오기
     */
    public Optional<Cake> getThisYearCake(Long userId) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        return cakeRepository.findByUserIdAndYear(userId, calendar.get(Calendar.YEAR));
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
    public List<Cake> getAllCake(Long userId) {
        return cakeRepository.findAllByUserId(userId);
    }
}
