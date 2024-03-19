package com.example.petstore.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue
    private Long eid;
    private String firstName;
    @Column(unique = true)
    private String phone;
    private String password;
    private String role;
}
