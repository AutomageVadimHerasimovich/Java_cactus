package com.example.petstore.controller;

import com.example.petstore.model.Employee;
import com.example.petstore.model.Pet;
import com.example.petstore.service.CounterService;
import com.example.petstore.service.PetstoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PetstoreControllerTest {

    @Mock
    PetstoreService petstoreService;

    @SuppressWarnings("unused")
    @Mock
    CounterService counterService;

    @InjectMocks
    PetstoreController petstoreController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(petstoreController).build();
    }

    @Test
    void testGetPets() throws Exception {
        when(petstoreService.getPets()).thenReturn(Arrays.asList(new Pet(), new Pet()));
        mockMvc.perform(get("/api/v1/petstore")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testSavePet() throws Exception {
        when(petstoreService.savePet(any(Pet.class))).thenReturn(new Pet());
        mockMvc.perform(post("/api/v1/petstore/savePet")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
void testSavePets() throws Exception {
    doNothing().when(petstoreService).savePets(anyList());
    mockMvc.perform(post("/api/v1/petstore/savePets")
            .contentType(MediaType.APPLICATION_JSON)
            .content("[{}, {}]"))
            .andExpect(status().isOk());
}

    @Test
    void testGetPetByPhone() throws Exception {
        when(petstoreService.getPetByPhone(anyString())).thenReturn(new Pet());
        mockMvc.perform(get("/api/v1/petstore/1234567890")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdatePet() throws Exception {
        when(petstoreService.updatePet(any(Pet.class))).thenReturn(new Pet());
        mockMvc.perform(put("/api/v1/petstore/updatePet")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeletePet() throws Exception {
        mockMvc.perform(delete("/api/v1/petstore/deletePet/1234567890")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testConnectPetToEmployee() throws Exception {
        when(petstoreService.connectPetToEmployee(anyString(), anyString())).thenReturn(new Employee());
        mockMvc.perform(patch("/api/v1/petstore/connectPetToEmployee/1234567890/employee/1234567890")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}