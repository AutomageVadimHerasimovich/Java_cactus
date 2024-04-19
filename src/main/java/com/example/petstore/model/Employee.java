package com.example.petstore.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;

/**
 * This class represents the Employee entity.
 */
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
