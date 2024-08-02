package learn.spring.api.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import learn.spring.api.models.Usuario;

public interface UserRepository extends JpaRepository<Usuario, Long>{
    Usuario findByEmail(String email);

    boolean existsByEmail(String email);
}
