package com.example.petstore.service;

import com.example.petstore.model.Employee;
import com.example.petstore.model.Pet;
import java.util.List;

/**
 * This interface represents the service layer for the Petstore application.
 */

public interface PetstoreService {
  List<Pet> getPets();

  Pet savePet(Pet pet);

  Pet getPetByPhone(String phone);

  Pet updatePet(Pet pet);

  Employee connectPetToEmployee(String petPhone, String employeePhone);

  void savePets(List<Pet> pets);

  void deletePet(String phone);
}