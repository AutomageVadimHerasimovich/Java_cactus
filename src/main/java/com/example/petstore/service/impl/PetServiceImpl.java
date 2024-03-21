package com.example.petstore.service.impl;

import com.example.petstore.model.Employee;
import com.example.petstore.model.Pet;
import com.example.petstore.repository.EmployeeRepository;
import com.example.petstore.repository.PetRepository;
import com.example.petstore.service.PetstoreService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@AllArgsConstructor
@Primary
public class PetServiceImpl implements PetstoreService {
    private final PetRepository repository;
    private final EmployeeRepository employeeRepository;

    @Override
    public List<Pet> getPets() {
        return repository.findAll();
    }

    @Override
    public Pet savePet(Pet pet) {
        return repository.save(pet);
    }

    @Override
    public Pet getPetByPhone(String phone) {
        return repository.findPetByPhone(phone);
    }

    @Override
    public Pet updatePet(Pet pet) {
        Pet existingPet = repository.findPetByPhone(pet.getPhone());
        if (existingPet != null) {
            existingPet.setName(pet.getName());
            existingPet.setAge(pet.getAge());
            existingPet.setUrl(pet.getUrl());
            existingPet.setStatus(pet.getStatus());
        }
        assert existingPet != null;
        return repository.save(existingPet);
    }
    @Override
    public Employee connectPetToEmployee(String petPhone, String employeePhone) {
        Employee existingEmployee = employeeRepository.findEmployeeByPhone(employeePhone);
        Pet existingPet = repository.findPetByPhone(petPhone);
        if (existingEmployee != null && existingPet != null) {
            List<Pet> pets = existingEmployee.getPets();
            if (pets.contains(existingPet)) {
                return existingEmployee;
            } else {
                pets.add(existingPet);
                existingEmployee.setPets(pets);
            }
        }
        assert existingEmployee != null;
        return employeeRepository.save(existingEmployee);
    }

    @Override
    @Transactional
    public void deletePet(String phone) {

        repository.deletePetByPhone(phone);
    }
}
