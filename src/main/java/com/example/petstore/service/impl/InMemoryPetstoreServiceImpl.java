package com.example.petstore.service.impl;

import com.example.petstore.model.Pet;
import com.example.petstore.repository.InMemoryPetDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class InMemoryPetstoreServiceImpl implements com.example.petstore.service.PetstoreService{
    private final InMemoryPetDAO repository;
    @Override
    public List<Pet> getPets() {
        return repository.getPets();
    }

    @Override
    public void savePet(Pet pet) {
        repository.savePet(pet);
    }

    @Override
    public Pet getPetById(long id) {
        return repository.getPetById(id);
    }

    @Override
    public Pet updatePet(Pet pet) {
        return repository.updatePet(pet);
    }

    @Override
    public void deletePet(long id) {
        repository.deletePet(id);
    }
}
