package com.librarySpring.librarySpring.Entities.Login;

import com.librarySpring.librarySpring.Entities.Login.model.CredentialsDTO;
import com.librarySpring.librarySpring.Entities.Login.services.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody CredentialsDTO credentials) {
        return loginService.execute(credentials);
    }

}
