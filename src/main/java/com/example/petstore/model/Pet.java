package com.example.petstore.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
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
}
