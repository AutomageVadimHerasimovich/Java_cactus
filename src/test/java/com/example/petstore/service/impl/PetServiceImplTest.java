package com.example.petstore.service.impl;

import com.example.petstore.model.Employee;
import com.example.petstore.model.Pet;
import com.example.petstore.repository.EmployeeRepository;
import com.example.petstore.repository.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class PetServiceImplTest {

    @Mock
    private PetRepository petRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private PetServiceImpl petService;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(petService).build();
    }

    @Test
    void shouldGetPets() {
        List<Pet> pets = Arrays.asList(new Pet(), new Pet());
        when(petRepository.findAll()).thenReturn(pets);

        List<Pet> result = petService.getPets();

        assertEquals(pets, result);
        verify(petRepository, times(1)).findAll();
    }

    @Test
    void shouldSavePet() {
        Pet pet = new Pet();
        when(petRepository.save(pet)).thenReturn(pet);

        Pet result = petService.savePet(pet);

        assertEquals(pet, result);
        verify(petRepository, times(1)).save(pet);
    }

    @Test
    void shouldGetPetByPhone() {
        String phone = "1234567890";
        Pet pet = new Pet();
        when(petRepository.findPetByPhone(phone)).thenReturn(pet);

        Pet result = petService.getPetByPhone(phone);

        assertEquals(pet, result);
        verify(petRepository, times(1)).findPetByPhone(phone);
    }

    @Test
    void shouldUpdatePet() {
    Pet pet = new Pet();
    pet.setPhone("1234567890"); // установите номер телефона
    when(petRepository.findPetByPhone(pet.getPhone())).thenReturn(pet);
    when(petRepository.save(pet)).thenReturn(pet);

    Pet result = petService.updatePet(pet);

    assertEquals(pet, result);
    verify(petRepository, times(1)).save(pet);
  }

    @Test
void shouldConnectPetToEmployee() {
    String petPhone = "1234567890";
    String employeePhone = "0987654321";
    Employee employee = new Employee();
    Pet pet = new Pet();
    when(petRepository.findPetByPhone(petPhone)).thenReturn(pet);
    when(employeeRepository.findEmployeeByPhone(employeePhone)).thenReturn(employee);
    when(employeeRepository.save(employee)).thenReturn(employee);

    Employee result = petService.connectPetToEmployee(petPhone, employeePhone);

    assertEquals(employee, result);
    verify(petRepository, times(1)).findPetByPhone(petPhone);
    verify(employeeRepository, times(1)).findEmployeeByPhone(employeePhone);
}

    @Test
    void shouldSavePets() {
        List<Pet> pets = Arrays.asList(new Pet(), new Pet());

        petService.savePets(pets);

        verify(petRepository, times(pets.size())).save(any(Pet.class));
    }

    @Test
    void shouldDeletePet() {
        String phone = "1234567890";
        when(petRepository.findPetByPhone(phone)).thenReturn(new Pet());

        petService.deletePet(phone);

        verify(petRepository, times(1)).deletePetByPhone(phone);
    }
}