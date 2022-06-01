package br.com.projeto.api.service;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.projeto.api.modelo.CreateUserResponse;
import br.com.projeto.api.modelo.Response;
import br.com.projeto.api.modelo.User;
import br.com.projeto.api.repositorio.UserRepository;

@Service
public class UserService {

    @Autowired
    private Response response;

    @Autowired
    private CreateUserResponse createUserResponse;

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public ResponseEntity<?> createUser(User obj) {
        Optional<User> user = repository.findByEmail(obj.getEmail());
        if(user.isPresent()) {
            createUserResponse.setMsg("Email já está em uso!");
            createUserResponse.setEmailExists(true);
            return new ResponseEntity<>(createUserResponse, HttpStatus.OK);    
        }
        if (obj.getName().equals("")) {
            createUserResponse.setMsg("O nome precisa ser preenchido");
            return new ResponseEntity<>(createUserResponse, HttpStatus.BAD_REQUEST);
        } else if (obj.getEmail().equals("")) {
            createUserResponse.setMsg("O email precisa ser preenchido");
            return new ResponseEntity<>(createUserResponse, HttpStatus.BAD_REQUEST);
        } else if (obj.getPassword().equals("")) {
            createUserResponse.setMsg("A senha precisa ser preenchida");
            return new ResponseEntity<>(createUserResponse, HttpStatus.BAD_REQUEST);
        } else {
            obj.setPassword(encoder.encode(obj.getPassword()));
            obj.setPersonNumber(createPersonNumber());
            return new ResponseEntity<>(repository.save(obj), HttpStatus.CREATED);
        }
    }

    
    public String createPersonNumber() {
        boolean loop = true;
        Random random = new Random();
        while (loop) {
            int i = random.nextInt();
            if(i < 0){
                i = i * -1;
            }
            String personNumber = Integer.toString(i);
            Optional<User> user = repository.findByPersonNumber(personNumber);
            if (!user.isPresent()) {
                loop = false;
                return personNumber;
            }
        }
        return null;
    }
    
    public ResponseEntity<?> findUsers() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> userExistis(User obj) {
        if (obj.getEmail() == null) {
            response.setMsg("Informe seu email");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        Optional<User> user = repository.findByEmail(obj.getEmail());
        if (user.isPresent()) {
            response.setMsg("Usuário existe");
            response.setExists(true);
            response.setEmail(obj.getEmail());
            response.setName(obj.getName());
            response.setPersonNumber(obj.getPersonNumber());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        response.setMsg("Usuário não existe");
        response.setExists(false);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteAllUsers() {
        repository.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    public ResponseEntity<?> editUser(User obj) {
        Optional<User> user = repository.findByPersonNumber(obj.getPersonNumber());
        if(user.isPresent()){
            User newUser = user.get();
            newUser.setName(obj.getName());
            newUser.setEmail(obj.getEmail());
            repository.save(newUser);
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("User não existe", HttpStatus.OK);
        }
    }

    public ResponseEntity<?> deleteUser(User obj){
        Optional<User> user = repository.findByEmail(obj.getEmail());
        if(user.isPresent()){
            repository.delete(user.get());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> editPassword(User obj) {
        Optional<User> user = repository.findByPersonNumber(obj.getPersonNumber());
        if(user.isPresent()){
            User newUser = user.get();
            newUser.setPassword(encoder.encode(obj.getPassword()));
            repository.save(newUser);
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("User não existe", HttpStatus.OK);
        }
    } 

}