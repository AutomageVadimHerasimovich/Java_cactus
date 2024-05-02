package com.example.petstore.controller;

import com.example.petstore.exception.MyExceptionHandler;
import com.example.petstore.model.Employee;
import com.example.petstore.model.Pet;
import com.example.petstore.service.CounterService;
import com.example.petstore.service.PetstoreService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class represents the controller layer for the Petstore application.
 */
@RestController
@RequestMapping("/api/v1/petstore")
@AllArgsConstructor
@MyExceptionHandler
public class  PetstoreController {
  private final PetstoreService service;
  private final CounterService counterService;
  public static final String COUNTER_MSG = "Counter = {}";
  private static final Logger LOGGER = LoggerFactory.getLogger(PetstoreController.class);

  private void logCounter() {
    LOGGER.info(COUNTER_MSG, counterService.incrementAndGet());
  }

  /**
   * Returns a list of pets.
   *
   * @return a list of pets.
   */
  @GetMapping
  public List<Pet> getPets() {
    logCounter();
    return service.getPets();
  }

  @PostMapping("savePet")
  public Pet savePet(@RequestBody Pet pet) {
    logCounter();
    return service.savePet(pet);
  }

  /**
   * Saves a list of pets.
   *
   * @param pets a list of pets.
   * @return a list of pets.
   */
  @PostMapping("savePets")
  public List<Pet> savePets(@RequestBody List<Pet> pets) {
    service.savePets(pets);
    logCounter();
    return pets;
  }

  @GetMapping("/{phone}")
  public Pet getPetByPhone(@PathVariable String phone) {
    logCounter();
    return service.getPetByPhone(phone);
  }

  @PutMapping("updatePet")
  public Pet updatePet(@RequestBody Pet pet) {
    logCounter();
    return service.updatePet(pet);
  }

  @DeleteMapping("/deletePet/{phone}")
  public void deletePet(@PathVariable String phone) {
    logCounter();
    service.deletePet(phone);
  }

  @PatchMapping("/connectPetToEmployee/{petPhone}/employee/{employeePhone}")
  public Employee connectPetToEmployee(@PathVariable String petPhone,
                                       @PathVariable String employeePhone) {
    logCounter();
    return service.connectPetToEmployee(petPhone, employeePhone);
  }
}