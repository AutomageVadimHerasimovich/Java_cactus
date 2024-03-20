package com.example.petstore.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "pets")
public class Pet {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String url;
    private int age;
    private String status;
    @Column(unique = true)
    private String phone;
    @ManyToMany
    @JoinTable(name = "employee_pets",
            joinColumns = @JoinColumn(name = "pet_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id") )
    private Set<Employee> employees = new HashSet<>();
}