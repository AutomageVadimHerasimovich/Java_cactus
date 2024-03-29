package com.example.petstore.service.impl;

import com.example.petstore.model.Employee;
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
    public Pet savePet(Pet pet) {
        return repository.savePet(pet);
    }

    @Override
    public Pet getPetByPhone(String phone){
        return repository.getPetByPhone(phone);
    }

    @Override
    public Pet updatePet(Pet pet) {
        return repository.updatePet(pet);
    }
    @Override
    public Employee connectPetToEmployee(String petPhone, String employeePhone) {
        return repository.connectPetToEmployee(petPhone, employeePhone);
    }
    @Override
    public void deletePet(String phone) {
        repository.deletePet(phone);
    }
}
