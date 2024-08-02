package learn.spring.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import learn.spring.api.models.Usuario;
import learn.spring.api.repositorys.UserRepository;

public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = repo.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("Usuário com o username "+username+" não encontrado");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                user.getRoles());
    }
    
}
