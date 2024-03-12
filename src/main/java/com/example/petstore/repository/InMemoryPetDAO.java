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

    public Pet savePet(Pet pet) {
        pets.add(pet);
        return pet;
    }


    public Pet getPetByPhone(String phone) {
        return pets.stream()
                .filter(element -> element.getPhone().equals(phone))
                .findFirst()
                .orElse(null);
    }


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


    public void deletePet(String phone) {
        var petToDelete = getPetByPhone(phone);
        if (petToDelete != null) {
            pets.remove(petToDelete);
        }
    }
}
