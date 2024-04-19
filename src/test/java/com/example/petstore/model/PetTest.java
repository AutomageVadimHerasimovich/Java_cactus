package com.example.petstore.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PetTest {

    private Pet pet;

    @BeforeEach
    void setUp() {
        pet = new Pet();
    }

    @Test
    void testId() {
        Long idValue = 1L;
        pet.setId(idValue);
        assertEquals(idValue, pet.getId());
    }

    @Test
    void testName() {
        String nameValue = "Test Name";
        pet.setName(nameValue);
        assertEquals(nameValue, pet.getName());
    }

    @Test
    void testUrl() {
        String urlValue = "Test Url";
        pet.setUrl(urlValue);
        assertEquals(urlValue, pet.getUrl());
    }

    @Test
    void testAge() {
        int ageValue = 5;
        pet.setAge(ageValue);
        assertEquals(ageValue, pet.getAge());
    }

    @Test
    void testStatus() {
        String statusValue = "Test Status";
        pet.setStatus(statusValue);
        assertEquals(statusValue, pet.getStatus());
    }

    @Test
    void testPhone() {
        String phoneValue = "Test Phone";
        pet.setPhone(phoneValue);
        assertEquals(phoneValue, pet.getPhone());
    }
}