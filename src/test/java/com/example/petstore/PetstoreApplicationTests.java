package com.example.petstore;

import com.example.petstore.model.Pet;
import com.example.petstore.service.PetstoreService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PetstoreApplicationTests {

    @Test
    void contextLoads() {
        PetstoreService service = new PetstoreService() {
            @Override
            public List<Pet> getPets() {
                return null;
            }

            @Override
            public void savePet(Pet pet) {

            }

            @Override
            public Pet getPetById(long id) {
                return null;
            }

            @Override
            public Pet updatePet(Pet pet) {
                return null;
            }

            @Override
            public void deletePet(long id) {

            }
        };
        List<Pet> pets = List.of(
                Pet.builder().id(1).name("Dog").age(12).url("https://tut.by").status("Available").build(),
                Pet.builder().id(2).name("Cat").age(5).url("https://onliner.by").status("Available").build(),
                Pet.builder().id(3).name("Parrot").age(2).url("https://google.com").status("Available").build()
        );
        Assertions.assertNotEquals(null, service.getPets());
        Assertions.assertNotEquals(null, service.getPetById(1));
        Assertions.assertNotEquals(null, service.updatePet(pets.get(0)));
    }

}
