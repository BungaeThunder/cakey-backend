package bungae.thunder.cakey.user.repository;

import bungae.thunder.cakey.user.domain.User;

import java.util.List;

public interface UserRepository {
    User save(User user);

    User findById(Long id);

    List<User> findAll();
}
