package com.librarySpring.librarySpring.Entities.Login;

import com.librarySpring.librarySpring.Entities.Login.model.CredentialsDTO;
import com.librarySpring.librarySpring.Entities.Login.services.LoginService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @MutationMapping
    public String login(@Argument CredentialsDTO credentials) {
        return loginService.execute(credentials);
    }

}
