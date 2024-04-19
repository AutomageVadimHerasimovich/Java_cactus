package com.example.petstore.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee();
    }

    @Test
    void testId() {
        Long idValue = 1L;
        employee.setId(idValue);
        assertEquals(idValue, employee.getId());
    }

    @Test
    void testFirstName() {
        String firstNameValue = "Test Name";
        employee.setFirstName(firstNameValue);
        assertEquals(firstNameValue, employee.getFirstName());
    }

    @Test
    void testPhone() {
        String phoneValue = "Test Phone";
        employee.setPhone(phoneValue);
        assertEquals(phoneValue, employee.getPhone());
    }

    @Test
    void testPassword() {
        String passwordValue = "Test Password";
        employee.setPassword(passwordValue);
        assertEquals(passwordValue, employee.getPassword());
    }

    @Test
    void testRole() {
        String roleValue = "Test Role";
        employee.setRole(roleValue);
        assertEquals(roleValue, employee.getRole());
    }

    @Test
    void testPets() {
        List<Pet> petsValue = new ArrayList<>();
        employee.setPets(petsValue);
        assertEquals(petsValue, employee.getPets());
    }
}