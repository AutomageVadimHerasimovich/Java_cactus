package com.example.petstore.service.impl;

import com.example.petstore.model.Employee;
import com.example.petstore.model.Pet;
import com.example.petstore.repository.InMemoryPetDao;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * The service class for the Pet entity.
 */
@Service
@AllArgsConstructor
@Slf4j
public class InMemoryPetstoreServiceImpl implements com.example.petstore.service.PetstoreService {
  private final InMemoryPetDao repository;

  @Override
  public List<Pet> getPets() {
    log.info("Getting all pets");
    return repository.getPets();
  }

  @Override
  public Pet savePet(Pet pet) {
    log.info("Pet saved: {}", pet);
    return repository.savePet(pet);
  }

  @Override
  public Pet getPetByPhone(String phone) {
    log.info("Getting pet by phone: {}", phone);
    return repository.getPetByPhone(phone);
  }

  @Override
  public Pet updatePet(Pet pet) {
    log.info("Pet updated: {}", pet);
    return repository.updatePet(pet);
  }

  @Override
  public Employee connectPetToEmployee(String petPhone, String employeePhone) {
    log.info("Connecting pet to employee");
    return repository.connectPetToEmployee(petPhone, employeePhone);
  }

  @Override
  public void savePets(List<Pet> pets) {
    repository.savePets(pets);
    log.info("Pets saved: {}", pets);
  }

  @Override
  public void deletePet(String phone) {
    repository.deletePet(phone);
    log.info("Pet deleted: {}", phone);
  }
}
