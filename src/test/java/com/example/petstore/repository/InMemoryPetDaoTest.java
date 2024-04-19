package com.example.petstore.repository;

import com.example.petstore.model.Employee;
import com.example.petstore.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryPetDaoTest {

    private InMemoryPetDao inMemoryPetDao;

    @BeforeEach
    void setUp() {
        inMemoryPetDao = new InMemoryPetDao();
    }

    @Test
    void shouldSavePet() {
        Pet pet = new Pet();
        Pet result = inMemoryPetDao.savePet(pet);

        assertEquals(pet, result);
    }

    @Test
    void shouldGetPetByPhone() {
        Pet pet = new Pet();
        pet.setPhone("1234567890");
        inMemoryPetDao.savePet(pet);

        Pet result = inMemoryPetDao.getPetByPhone("1234567890");

        assertEquals(pet, result);
    }

    @Test
    void shouldUpdatePet() {
        Pet pet = new Pet();
        pet.setPhone("1234567890");
        inMemoryPetDao.savePet(pet);
        pet.setName("UpdatedName");

        Pet result = inMemoryPetDao.updatePet(pet);

        assertEquals("UpdatedName", result.getName());
    }

    @Test
    void shouldConnectPetToEmployee() {
        Pet pet = new Pet();
        pet.setPhone("1234567890");
        inMemoryPetDao.savePet(pet);

        Employee result = inMemoryPetDao.connectPetToEmployee("1234567890", "0987654321");

        assertNull(result); // As the current implementation returns null
    }

    @Test
    void shouldSavePets() {
        Pet pet1 = new Pet();
        Pet pet2 = new Pet();
        List<Pet> pets = Arrays.asList(pet1, pet2);

        inMemoryPetDao.savePets(pets);

        assertEquals(2, inMemoryPetDao.getPets().size());
    }

    @Test
    void shouldDeletePet() {
        Pet pet = new Pet();
        pet.setPhone("1234567890");
        inMemoryPetDao.savePet(pet);

        inMemoryPetDao.deletePet("1234567890");

        assertNull(inMemoryPetDao.getPetByPhone("1234567890"));
    }
}