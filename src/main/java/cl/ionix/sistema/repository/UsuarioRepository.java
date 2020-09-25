package cl.ionix.sistema.repository;

import cl.ionix.sistema.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<User,String> {

    User findByEmail(String email);
}
