package br.com.projeto.api.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.projeto.api.modelo.User;
import br.com.projeto.api.repositorio.UserRepository;

@Service
public class CustomUserDetails implements UserDetailsService{
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> opt = userRepository.findByName(username);
        User user = null;
        if(opt.isPresent()){
            user = opt.get();
        }
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserMain(user);
    }
    
}
