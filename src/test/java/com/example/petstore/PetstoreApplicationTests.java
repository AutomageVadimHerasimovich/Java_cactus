package com.example.petstore;

import com.example.petstore.model.Pet;
import com.example.petstore.repository.PetRepository;
import com.example.petstore.service.impl.PetServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

class PetServiceImplTest {

    @Mock
    PetRepository repository;

    @InjectMocks
    PetServiceImpl petService;

    @Test
    void shouldUpdatePetWhenExists() {
        Pet existingPet = new Pet();
        existingPet.setPhone("1234567890");
        existingPet.setName("Old Name");
        existingPet.setAge(5);

        Pet newPet = new Pet();
        newPet.setPhone("1234567890");
        newPet.setName("New Name");
        newPet.setAge(6);

        when(repository.findPetByPhone(anyString())).thenReturn(existingPet);

        petService.updatePet(newPet);

        verify(repository, times(1)).save(newPet);
    }

    @Test
    void shouldNotUpdatePetWhenDoesNotExist() {
        Pet newPet = new Pet();
        newPet.setPhone("1234567890");
        newPet.setName("New Name");
        newPet.setAge(6);

        when(repository.findPetByPhone(anyString())).thenReturn(null);

        petService.updatePet(newPet);

        verify(repository, times(0)).save(newPet);
    }
}