package com.example.petstore.repository;

import com.example.petstore.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface represents the repository layer for the Pet entity.
 */
public interface PetRepository extends JpaRepository<Pet, Long> {

  void deletePetByPhone(String phone);

  Pet findPetByPhone(String phone);
}
