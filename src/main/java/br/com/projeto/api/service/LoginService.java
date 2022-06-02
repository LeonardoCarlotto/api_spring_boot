package br.com.projeto.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.projeto.api.modelo.User;
import br.com.projeto.api.repositorio.UserRepository;

@Service
public class LoginService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public String getPass(User obj) {
        Optional<User> user = repository.findByEmail(obj.getEmail());
        if(encoder.matches(obj.getPassword(), user.get().getPassword())){
            return user.get().getPassword();
        }else{
            return null;
        }
    }

    public boolean existsUser(User user) {
       return repository.existsByEmail(user.getEmail());
    }
}