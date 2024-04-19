package com.example.petstore.repository;

import com.example.petstore.model.Employee;
import com.example.petstore.model.Pet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import lombok.Getter;
import org.springframework.stereotype.Repository;

/**
 * A data access object for pets.
 */
@Getter
@Repository
public class InMemoryPetDao {

  private final List<Pet> pets = new ArrayList<>();
  private final List<String> employees = new ArrayList<>();

  public Pet savePet(Pet pet) {
    pets.add(pet);
    return pet;
  }

  /**
   * Returns the pet with the given phone number.
   *
   * @param phone the phone number of the pet
   * @return the pet with the given phone number
   */
  public Pet getPetByPhone(String phone) {
    return pets.stream()
      .filter(element -> element.getPhone().equals(phone))
      .findFirst()
      .orElse(null);
  }

  /**
   * Updates the given pet.
   *
   * @param pet the pet to update
   * @return the updated pet
   */
  public Pet updatePet(Pet pet) {
    var petToUpdate = IntStream.range(0, pets.size())
        .filter(index -> pets.get(index).getPhone().equals(pet.getPhone()))
        .findFirst()
        .orElse(-1);
    if (petToUpdate > -1) {
      pets.set(petToUpdate, pet);
      return pet;
    }
    return null;
  }

  /**
   * Connects the pet with the given phone number to the employee with the given phone number.
   *
   * @param petPhone the phone number of the pet
   * @param employeePhone the phone number of the employee
   * @return the employee with the connected pet
   */
  public Employee connectPetToEmployee(String petPhone, String employeePhone) {
    var petToConnect = IntStream.range(0, pets.size())
        .filter(index -> pets.get(index).getPhone().equals(petPhone))
        .findFirst()
        .orElse(-1);
    if (petToConnect > -1 && !employees.contains(employeePhone)) {
        // If the pet exists and the employee's phone number is not in the list, add it to the list
        employees.add(employeePhone);
    }
    return null;
}

  /**
   * Saves the given list of pets.
   *
   * @param pets the list of pets to save
   */
  public void savePets(List<Pet> pets) {
    this.pets.addAll(pets);
  }

  /**
   * Deletes the pet with the given phone number.
   *
   * @param phone the phone number of the pet to delete
   */
  public void deletePet(String phone) {
    var petToDelete = getPetByPhone(phone);
    if (petToDelete != null) {
      pets.remove(petToDelete);
    }
  }
}
