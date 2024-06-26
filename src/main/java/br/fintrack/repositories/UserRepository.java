package br.fintrack.repositories;

import java.util.List;

import br.fintrack.models.User;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@EnableScan()
public interface UserRepository extends CrudRepository<User, String> {
    List<User> findByEmail(String email);
}
