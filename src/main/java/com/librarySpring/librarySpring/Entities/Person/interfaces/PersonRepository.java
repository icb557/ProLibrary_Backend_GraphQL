package com.librarySpring.librarySpring.Entities.Person.interfaces;

import com.librarySpring.librarySpring.Entities.Person.model.Person;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findByUsername(String username);

    List<Person> findByUsernameContainingIgnoreCase(String username);

    @Modifying
    @Transactional
    @Query("UPDATE Person p SET p.username = :newUsername, p.password = :newPassword, p.role = :newRole WHERE p.id = :id")
    void updatePerson(@Param("id") Integer id, @Param("newUsername") String newUsername, @Param("newPassword") String newPassword, @Param("newRole") String newRole);
}
