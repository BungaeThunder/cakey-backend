package bungae.thunder.cakey.service;

import bungae.thunder.cakey.domain.User;
import bungae.thunder.cakey.repository.MemoryUserRepository;
import bungae.thunder.cakey.repository.UserRepository;
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
    public Long signUp(User user) {
        userRepository.save(user);
        return user.getId();
    }

    /**
     * 전체 회원 조회
     */
    public List<User> findMembers() {
        return userRepository.findAll();
    }

    public Optional<User> findOne(Long userId) {
        return userRepository.findById(userId);
    }
}
