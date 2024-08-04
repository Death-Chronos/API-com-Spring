package learn.spring.api.controllers;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import learn.spring.api.models.Role;
import learn.spring.api.models.Usuario;
import learn.spring.api.repositorys.RoleRepository;
import learn.spring.api.security.MyUserDetailsService;

@RestController
public class UserController {

    @Autowired
    private MyUserDetailsService userService;

    @Autowired
    private RoleRepository roleRepo;

    @PostMapping("/register")
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody Usuario user) {
        System.out.println(user.getPassword());

        Role role = roleRepo.findByName("ROLE_USER");
        if (role == null) {
            role = new Role();
            role.setName("ROLE_USER");
            role = roleRepo.save(role);
        }

        HashSet<Role> roles = new HashSet<Role>();
        roles.add(role);

        user.setRoles(roles);;

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));

    }

}
