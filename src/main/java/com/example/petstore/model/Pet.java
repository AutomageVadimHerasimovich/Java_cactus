package com.example.petstore.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pets")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String url;
    private int age;
    private String status;
    @Column(unique = true, nullable = false)
    private String phone;
}