package learn.spring.api.config;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import learn.spring.api.models.Role;
import learn.spring.api.models.Usuario;
import learn.spring.api.repositorys.RoleRepository;
import learn.spring.api.repositorys.UserRepository;

@Configuration
public class AdminCreator implements CommandLineRunner {

    @Autowired
    UserRepository repo;

    @Autowired
    RoleRepository roleRepo;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (!repo.existsByEmail("jv@gmail.com")) {
            Usuario adm = new Usuario("jv@gmail.com",passwordEncoder.encode("12345678"));

            HashSet<Role> roles = new HashSet<Role>();
            Role role = new Role("ADMIN");
            roles.add(role);

            roleRepo.save(role);

            adm.setRoles(roles);
            repo.save(adm);
        }else{
            System.out.println("Admin já existe");
        }
    }
    
}
