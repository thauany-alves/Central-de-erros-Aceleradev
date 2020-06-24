package br.com.aceleradev.java.centraldeerros.repository;

import br.com.aceleradev.java.centraldeerros.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);

    Optional<User> findByLogin(String login);

    Optional<User> findById(Long id);


}
