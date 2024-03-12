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
}