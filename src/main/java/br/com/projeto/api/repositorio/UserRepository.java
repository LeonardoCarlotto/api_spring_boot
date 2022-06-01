package br.com.projeto.api.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.api.modelo.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findAll();

    Optional<User> findByName(String name);

    Optional<User> findByEmail(String email);

    Optional<User> findByPersonNumber(String personNumber);

}
