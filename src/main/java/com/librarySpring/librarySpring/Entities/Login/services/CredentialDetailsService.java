package com.librarySpring.librarySpring.Entities.Login.services;

import com.librarySpring.librarySpring.Entities.Person.interfaces.PersonRepository;
import com.librarySpring.librarySpring.Entities.Person.model.Person;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CredentialDetailsService implements UserDetailsService {

    private final PersonRepository repository;

    public CredentialDetailsService(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        //add roles and authorities

        return User
                .withUsername(person.getUsername())
                .password(person.getPassword())
                .roles(person.getRole().toUpperCase())
                .build();
    }
}
