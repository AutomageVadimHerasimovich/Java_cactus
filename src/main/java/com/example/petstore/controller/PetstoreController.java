package com.example.petstore.controller;

import com.example.petstore.service.PetstoreService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.petstore.model.Pet;
import java.util.List;

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
    public String  savePet(@RequestBody Pet pet) {
        service.savePet(pet);
        return "Pet saved successfully";
    }
    @GetMapping("/{id}")
    public Pet getPetById(@PathVariable long id) {
        return service.getPetById(id);
    }
    @PutMapping("updatePet")
    public Pet updatePet(@RequestBody Pet pet) {
        return service.updatePet(pet);
    }
    @DeleteMapping("/deletePet/{id}")
    public void deletePet(@PathVariable long id) {
        service.deletePet(id);
    }
}