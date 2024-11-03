package learn.spring.api.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import learn.spring.api.models.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {
    boolean existsByName(String name);

    Role findByName(String string);
}
