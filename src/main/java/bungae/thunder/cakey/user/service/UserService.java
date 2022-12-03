package bungae.thunder.cakey.user.service;

import bungae.thunder.cakey.user.domain.User;
import bungae.thunder.cakey.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // 컴포넌트 스캔 방식
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 회원가입
     */
    public Long createUser(User user) {
        userRepository.save(user);
        return user.getId();
    }

    /**
     * 전체 회원 조회
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * 특정 회원 조회
     */
    public Optional<User> getUser(Long userId) {
        return userRepository.findById(userId);
    }
}
