package bungae.thunder.cakey.service;

import bungae.thunder.cakey.domain.User;
import bungae.thunder.cakey.repository.MemoryUserRepository;
import bungae.thunder.cakey.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserService {
    private final UserRepository userRepository = new
            MemoryUserRepository();

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
