package learn.spring.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import learn.spring.api.models.Usuario;
import learn.spring.api.repositorys.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = repo.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("Usuário com o email " + username + " não encontrado");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                user.getRoles());
    }

     public Usuario save(Usuario usuario) {
         usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
         return repo.save(usuario);

     }
}
