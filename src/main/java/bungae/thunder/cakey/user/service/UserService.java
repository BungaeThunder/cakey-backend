package bungae.thunder.cakey.user.service;

import bungae.thunder.cakey.user.domain.User;
import bungae.thunder.cakey.user.exception.UserNotFoundException;
import bungae.thunder.cakey.user.repository.UserJpaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // 컴포넌트 스캔 방식
@Transactional
public class UserService {
    private final UserJpaRepository userRepository;

    @Autowired
    public UserService(UserJpaRepository userRepository) {
        this.userRepository = userRepository;
    }

    /** 회원가입 */
    public User createUser(User user) {
        return userRepository.save(user);
    }

    /** 전체 회원 조회 */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /** 특정 회원 조회 */
    public User getUser(Long userId) {
        return userRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException("유저가 존재하지 않습니다"));
    }
}
