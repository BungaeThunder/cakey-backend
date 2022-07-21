package bungae.thunder.cakey.repository;

import bungae.thunder.cakey.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
