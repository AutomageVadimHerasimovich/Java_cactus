package com.example.petstore.repository;

import com.example.petstore.model.Pet;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
@Getter
@Repository
public class InMemoryPetDAO {

    private final List<Pet> pets = new ArrayList<>();


    public void savePet(Pet pet) {
        pets.add(pet);
    }


    public Pet getPetById(long id) {
        return pets.stream()
                .filter(pet -> pet.getId() == id)
                .findFirst()
                .orElse(null);
    }


    public Pet updatePet(Pet pet) {
        var petToUpdate = IntStream.range(0, pets.size())
                .filter(i -> pets.get(i).getId() == pet.getId())
                .findFirst()
                .orElse(-1);
        if (petToUpdate != -1) {
            pets.set(petToUpdate, pet);
            return pet;
        }
        return null;
    }


    public void deletePet(long id) {
        var petToDelete = getPetById(id);
        if (petToDelete != null) {
            pets.remove(petToDelete);
        }
    }
}
