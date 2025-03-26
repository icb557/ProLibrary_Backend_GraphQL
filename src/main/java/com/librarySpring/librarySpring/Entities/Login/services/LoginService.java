package com.librarySpring.librarySpring.Entities.Login.services;

import com.librarySpring.librarySpring.Entities.Login.model.CredentialsDTO;
import com.librarySpring.librarySpring.Interfaces.Command;
import com.librarySpring.librarySpring.Security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements Command<CredentialsDTO, String> {

    private final AuthenticationManager manager;

    public LoginService(AuthenticationManager manager) {
        this.manager = manager;
    }

    @Override
    public ResponseEntity<String> execute(CredentialsDTO credentials) {
        //this token is different than JWT
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                credentials.getUsername(),
                credentials.getPassword()
        );

        //this will fault if credentials not valid
        Authentication authentication = manager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwtToken = JwtUtil.generateToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(jwtToken);
    }


}
