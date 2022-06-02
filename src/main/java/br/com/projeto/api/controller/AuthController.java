package br.com.projeto.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.api.modelo.User;
import br.com.projeto.api.service.LoginService;

@RestController
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private LoginService loginService;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user) throws Exception {
		
		Authentication authObject = null;
		try {
			if(!loginService.existsUser(user)){
				return new ResponseEntity<>("Usuário não existe.", HttpStatus.OK);
			}
			String password = loginService.getPass(user);
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), password));
			SecurityContextHolder.getContext().setAuthentication(authObject);
		} catch (BadCredentialsException e) {
			return new ResponseEntity<>("Senha incorreta.", HttpStatus.OK);
		}
		return new ResponseEntity<>("Login com Sucesso!", HttpStatus.OK);
		
	}
	
}