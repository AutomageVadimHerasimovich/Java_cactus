package com.example.petstore.service;

import com.example.petstore.model.Pet;

import java.util.List;


public interface PetstoreService {
    List<Pet> getPets();
    void savePet(Pet pet);
    Pet getPetById(long id);
    Pet updatePet(Pet pet);
    void deletePet(long id);
}