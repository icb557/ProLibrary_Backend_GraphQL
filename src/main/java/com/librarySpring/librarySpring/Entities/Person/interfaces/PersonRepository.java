package com.librarySpring.librarySpring.Entities.Person.interfaces;

import com.librarySpring.librarySpring.Entities.Person.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    Person findByUsername(String username);

}
