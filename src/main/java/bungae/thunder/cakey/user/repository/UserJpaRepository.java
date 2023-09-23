package bungae.thunder.cakey.user.repository;

import bungae.thunder.cakey.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
