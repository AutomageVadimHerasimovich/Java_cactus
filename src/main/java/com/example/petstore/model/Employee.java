package com.example.petstore.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    @Column(unique = true, nullable = false)
    private String phone;
    private String password;
    private String role;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private List<Pet> pets;
}
