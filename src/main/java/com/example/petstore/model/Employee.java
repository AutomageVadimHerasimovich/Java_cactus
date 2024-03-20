package com.example.petstore.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    @Column(unique = true)
    private String phone;
    private String password;
    private String role;
    @ManyToMany
    @JoinTable(name = "employee_pets",
            joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "pet_id", referencedColumnName = "id") )
    private Set<Pet> pets = new HashSet<>();
}
