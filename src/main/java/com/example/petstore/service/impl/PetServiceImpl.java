package com.example.petstore.service.impl;

import com.example.petstore.aspect.Logged;
import com.example.petstore.model.Employee;
import com.example.petstore.model.Pet;
import com.example.petstore.repository.EmployeeRepository;
import com.example.petstore.repository.PetRepository;
import com.example.petstore.service.PetstoreService;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

/**
 * The service class for the Pet entity.
 */
@Service
@AllArgsConstructor
@Primary
@Slf4j
public class PetServiceImpl implements PetstoreService {
  private final PetRepository petRepository;
  private final EmployeeRepository employeeRepository;

  @Logged
  @Override
  public List<Pet> getPets() {
    log.info("Getting all pets");
    return petRepository.findAll();
  }

  @Logged
  @Override
  public Pet savePet(Pet pet) {
    log.info("Pet saved: {}", pet);
    return petRepository.save(pet);
  }

  @Logged
  @Override
  public Pet getPetByPhone(String phone) {
    log.info("Getting pet by phone: {}", phone);
    return petRepository.findPetByPhone(phone);
  }

  @Logged
  @Override
  public Pet updatePet(Pet pet) {
    Pet existingPet = petRepository.findPetByPhone(pet.getPhone());
    if (existingPet != null) {
      existingPet.setName(pet.getName());
      existingPet.setAge(pet.getAge());
      existingPet.setUrl(pet.getUrl());
      existingPet.setStatus(pet.getStatus());
    }
    assert existingPet != null;
    log.info("Pet updated: {}", existingPet);
    return petRepository.save(existingPet);
  }

  @Logged
  @Override
  public Employee connectPetToEmployee(String petPhone, String employeePhone) {
    Employee existingEmployee = employeeRepository.findEmployeeByPhone(employeePhone);
    Pet existingPet = petRepository.findPetByPhone(petPhone);
    if (existingEmployee != null && existingPet != null) {
      List<Pet> pets = existingEmployee.getPets();
      if (pets == null) {
        pets = new ArrayList<>();
        existingEmployee.setPets(pets);
      }
      if (!pets.contains(existingPet)) {
        pets.add(existingPet);
      }
    }
    assert existingEmployee != null;
    log.info("Pet connected to employee: {}", existingEmployee);
    return employeeRepository.save(existingEmployee);
  }

  @Logged
  public void savePets(List<Pet> pets) {
    log.info("Saving multiple pets");
    pets.forEach(this::savePet);
  }

  @Logged
  @Override
  @Transactional
  public void deletePet(String phone) {
    if (petRepository.findPetByPhone(phone) == null) {
      log.info("Pet not found by phone: {}", phone);
      throw new NotFoundException("Pet not found by phone: " + phone);
    }
    petRepository.deletePetByPhone(phone);
    log.info("Pet deleted by phone: {}", phone);
  }
}
