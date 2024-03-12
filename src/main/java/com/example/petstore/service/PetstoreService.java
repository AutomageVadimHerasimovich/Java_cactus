package com.example.petstore.service;

import com.example.petstore.model.Pet;

import java.util.List;


public interface PetstoreService {
    List<Pet> getPets();
    Pet savePet(Pet pet);
    Pet getPetByPhone(String phone);
    Pet updatePet(Pet pet);
    void deletePet(String phone);
}