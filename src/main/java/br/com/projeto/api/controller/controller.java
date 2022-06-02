package br.com.projeto.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.api.modelo.User;
import br.com.projeto.api.service.UserService;

@RestController
@Controller
public class controller {

    @Autowired
    private UserService service;

    @GetMapping("/dashboard")
	public String dashboard() {
		return "Displaying the dashboard page contents";
	}
	
	@GetMapping("/home")
	public String home() {
		return "Displaying the home page contents";
	}

    @PostMapping("/user")
    public ResponseEntity<?> user(@Valid @RequestBody User user){
        return service.createUser(user);
    }

    
    @GetMapping("/users")
    public ResponseEntity<?> select(){
        return service.findUsers();
    }

    @PostMapping("/user/existis")
    public ResponseEntity<?> existis(@RequestBody User user){
        return service.userExistis(user);
    }

    @PostMapping("/user/delete")
    public ResponseEntity<?> delete(@RequestBody User user){
        return service.deleteUser(user);
    }
    
    @PostMapping("/user/delete/all")
    public ResponseEntity<?> delete(){
        return service.deleteAllUsers();
    }

    @PostMapping("/user/edit")
    public ResponseEntity<?> editUser(@RequestBody User user){
        return service.editUser(user);
    }

    @PostMapping("/user/editPassword")
    public ResponseEntity<?> editPassword(@RequestBody User user){
        return service.editPassword(user);
    }

}
