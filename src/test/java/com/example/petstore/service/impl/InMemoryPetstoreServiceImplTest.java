package com.example.petstore.service.impl;

import com.example.petstore.model.Employee;
import com.example.petstore.model.Pet;
import com.example.petstore.repository.InMemoryPetDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class InMemoryPetstoreServiceImplTest {

    @Mock
    private InMemoryPetDao petDao;

    @InjectMocks
    private InMemoryPetstoreServiceImpl petstoreService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldGetPets() {
        List<Pet> pets = Arrays.asList(new Pet(), new Pet());
        when(petDao.getPets()).thenReturn(pets);

        List<Pet> result = petstoreService.getPets();

        assertEquals(pets, result);
        verify(petDao, times(1)).getPets();
    }

    @Test
    void shouldSavePet() {
        Pet pet = new Pet();
        when(petDao.savePet(pet)).thenReturn(pet);

        Pet result = petstoreService.savePet(pet);

        assertEquals(pet, result);
        verify(petDao, times(1)).savePet(pet);
    }

    @Test
    void shouldGetPetByPhone() {
        String phone = "1234567890";
        Pet pet = new Pet();
        when(petDao.getPetByPhone(phone)).thenReturn(pet);

        Pet result = petstoreService.getPetByPhone(phone);

        assertEquals(pet, result);
        verify(petDao, times(1)).getPetByPhone(phone);
    }

    @Test
    void shouldUpdatePet() {
        Pet pet = new Pet();
        when(petDao.updatePet(pet)).thenReturn(pet);

        Pet result = petstoreService.updatePet(pet);

        assertEquals(pet, result);
        verify(petDao, times(1)).updatePet(pet);
    }

    @Test
    void shouldConnectPetToEmployee() {
        String petPhone = "1234567890";
        String employeePhone = "0987654321";
        Employee employee = new Employee();
        when(petDao.connectPetToEmployee(petPhone, employeePhone)).thenReturn(employee);

        Employee result = petstoreService.connectPetToEmployee(petPhone, employeePhone);

        assertEquals(employee, result);
        verify(petDao, times(1)).connectPetToEmployee(petPhone, employeePhone);
    }

    @Test
    void shouldSavePets() {
        List<Pet> pets = Arrays.asList(new Pet(), new Pet());

        petstoreService.savePets(pets);

        verify(petDao, times(1)).savePets(pets);
    }

    @Test
    void shouldDeletePet() {
        String phone = "1234567890";

        petstoreService.deletePet(phone);

        verify(petDao, times(1)).deletePet(phone);
    }
}