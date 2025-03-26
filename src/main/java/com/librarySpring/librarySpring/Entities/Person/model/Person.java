package com.librarySpring.librarySpring.Entities.Person.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

@Entity
@Table(name="people", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
@Data
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @NotNull
    @Column(name="username", unique = true)
    private String username;

    @NotNull
    @Column(name="password")
    private String password;

    @NotNull
    @Column(name="role")
    private String role;
}
