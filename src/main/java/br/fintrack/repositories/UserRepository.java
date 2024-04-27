package br.fintrack.repositories;

import java.util.List;

import br.fintrack.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Short> {
    List<User> findByid(Short id);
}
