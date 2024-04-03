package com.example.petstore.controller;

import com.example.petstore.model.Employee;
import com.example.petstore.model.Pet;
import com.example.petstore.service.PetstoreService;
import java.util.List;
import lombok.AllArgsConstructor;
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
public class  PetstoreController {
  private final PetstoreService service;

  @GetMapping
  public List<Pet> getPets() {
    return service.getPets();
  }

  @PostMapping("savePet")
  public Pet savePet(@RequestBody Pet pet) {
    return service.savePet(pet);
  }

  @GetMapping("/{phone}")
  public Pet getPetByPhone(@PathVariable String phone) {
    return service.getPetByPhone(phone);
  }

  @PutMapping("updatePet")
  public Pet updatePet(@RequestBody Pet pet) {
    return service.updatePet(pet);
  }

  @DeleteMapping("/deletePet/{phone}")
  public void deletePet(@PathVariable String phone) {
    service.deletePet(phone);
  }

  @PatchMapping("/connectPetToEmployee/{petPhone}/employee/{employeePhone}")
  public Employee connectPetToEmployee(@PathVariable String petPhone,
                                       @PathVariable String employeePhone) {
    return service.connectPetToEmployee(petPhone, employeePhone);
  }
}